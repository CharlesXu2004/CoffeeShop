<template>
    <div class="beverage-order">
        <div class="background-decoration">
            <div class="floating-circle circle-1"></div>
            <div class="floating-circle circle-2"></div>
        </div>
        
        <el-card class="order-card">
            
            <template #header>
                <div class="card-header">
                    <img src="@/assets/imgs/drink.png" alt="" style="width: 50px; height: 50px; position: relative;">
                    <span class="header-text">售货小店</span>
                </div>
            </template>
            
            <el-form :model="data" class="order-form">
                <el-form-item label="饮品：" class="form-item-custom">
                    <el-input 
                        v-model="data.json.beverageName" 
                        placeholder="请输入饮品名称"
                        clearable
                        class="custom-input"
                    >
                    </el-input>
                </el-form-item>
                
                <div v-for="(additive, index) in data.json.additives" :key="index" class="additive-row">
                    <div class="form-inline" style="display: flex; justify-content: space-between;">
                        <el-form-item :label="`配料${index + 1}：`" class="form-item-custom">
                            <el-input 
                                v-model="additive.name" 
                                placeholder="请输入配料名称"
                                clearable
                                class="custom-input"
                                @input="handleAdditiveInput(index)"
                                @blur="validateName(index)"
                            >
                            </el-input>
                        </el-form-item>
                        
                        <el-form-item label="份数：" class="form-item-custom">
                            <el-input 
                                v-model="additive.num" 
                                placeholder="请输入配料名称"
                                clearable
                                :min="1" 
                                :max="20"
                                controls-position="right"
                                class="custom-input"
                                @input="handleAdditiveInput(index)"
                            />
                        </el-form-item>

                        <el-button 
                            v-if="data.json.additives.length > 1" 
                            type="danger" 
                            size="small" 
                            @click="removeAdditive(index)"
                            style="margin-left: 10px;"
                        >
                            删除
                        </el-button>

                    </div>
                </div>

                
                <div class="button-group">
                    <el-button type="primary" @click="submitOrder" class="submit-btn">
                        <span>✨ 提交订单 </span>
                    </el-button>
                    <el-button @click="resetForm" class="reset-btn">
                        <span>🔄 重置订单 </span>
                    </el-button>
                </div>
                
            </el-form>
            
            <div class="horizontal-divider"></div>
            
            <div class="order-result" v-if="data.order">
                <div class="result-header">
                    <span class="result-icon">📋</span>
                    <span class="result-title">订单详情</span>
                </div>
                <div class="result-content">

                    <div class="result-item">
                        <span class="label">您所点的是：</span>
                        <span class="value description">{{ data.order.description }}</span>
                    </div>

                    <el-table :data="data.order.goods" style="width: 100%" border show-summary :summary-method="getSummaries">
                        <el-table-column label="商品名称" align="center">
                            <template #default="scope">
                                <span class="item-name">{{ scope.row.name }}</span>
                            </template>
                        </el-table-column>
                        <el-table-column label="单价" align="center">
                            <template #default="scope">
                                <span class="price-text">¥ {{ scope.row.price }}</span>
                            </template>
                        </el-table-column>
                        <el-table-column label="数量" align="center">
                            <template #default="scope">
                                <span class="quantity-text">{{ scope.row.num }}</span>
                            </template>
                        </el-table-column>
                        <el-table-column label="小计" align="center">
                            <template #default="scope">
                                <span class="subtotal-text">¥ {{ scope.row.price * scope.row.num }}</span>
                            </template>
                        </el-table-column>
                    </el-table>

                    <div class="result-item price-item" >
                        <span class="label">总计价格是：</span>
                        <span class="value price">¥ {{ data.order.cost }}</span>
                    </div>
                </div>
            </div>
        </el-card>
    </div>
</template>

<script setup>
    import { reactive, ref } from "vue";
    import { User, Lock } from "@element-plus/icons-vue";
    import request from "@/utils/request.js";
    import {ElMessage} from "element-plus";
    import router from "@/router/index.js";

    // 表单数据
    const data = reactive({
        json : {
            "beverageName": '',
            "additives": [
                { "name": '', "num": null }
            ]
        },
        order: null
    })

    // 提交订单
    const submitOrder = () => {

        // 验证表单
        if (data.json.additives.length - 1 === 0) {
            ElMessage.warning('请至少添加一个配料')
            return
        }

        // 留存最后一个之前的 additives
        const lastAdditive = data.json.additives[data.json.additives.length - 1]
        data.json.additives.pop()

        request.post('/order/processOrder', data.json).then(res => {
            if (res.code === '200') {
                data.order = res.data
            } 
            else {
                ElMessage.error(res.msg)
            }
        })

        // 恢复最后一个配料行
        data.json.additives.push(lastAdditive)
    }

    // 重置表单
    const resetForm = () => {
        data.json = {
            "beverageName": '',
            "additives": [
                { "name": '', "num": null }
            ]
        }
        data.order = null
    }

    const handleAdditiveInput = (index) => {
        const currentAdditive = data.json.additives[index]
        
        if (currentAdditive.name.trim() 
            && currentAdditive.num > 0
            && index === data.json.additives.length - 1) {
            data.json.additives.push({ name: '', num: null })
        }
        
        // 如果当前配料为空，且不是第一个配料，则删除后面所有空的配料行
        if (!(currentAdditive.name.trim() && currentAdditive.num > 0) 
            && index !== data.json.additives.length - 1) {
            // 保留之前有内容的行
            const newAdditives = []
            for (let i = 0; i < index; i++) newAdditives.push(data.json.additives[i])
            // 当前行变为空行
            newAdditives.push({ name: '', num: null })
            data.json.additives.splice(0, data.json.additives.length, ...newAdditives)
        }
    }

    const validateName = (index) => {
        const currentAdditive = data.json.additives[index]

        // 不能是重复的配料名
        if (data.json.additives.filter(a => a.name.trim() === currentAdditive.name.trim()).length > 1) {
            ElMessage.error('配料名称不能重复')
            if (index === data.json.additives.length - 1) {
                currentAdditive.name = ''
                currentAdditive.num = null
            }
            else {
                const newAdditives = []
                for (let i = 0; i < index; i++) newAdditives.push(data.json.additives[i])
                // 当前行变为空行
                newAdditives.push({ name: '', num: null })
                data.json.additives.splice(0, data.json.additives.length, ...newAdditives)
            }
        }
    }

    const removeAdditive = (index) => {
        data.json.additives.splice(index, 1)
    }

    // 自定义合计行
    const getSummaries = (param) => {
        const { columns } = param
        const sums = []
        
        columns.forEach((column, index) => {
            if (index === 0) {
                sums[index] = '总计'
            } 
            else if (index === 3) {
                sums[index] = `¥ ${data.order.cost}`
            }
            else {
                sums[index] = '-'
            }
        })
        
        return sums
    }

</script>

<style scoped>

    @import "@/assets/css/main.css";


</style>