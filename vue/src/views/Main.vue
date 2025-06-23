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
                        v-model="data.beverageName" 
                        placeholder="请输入饮品名称"
                        clearable
                        class="custom-input"
                    >
                    </el-input>
                </el-form-item>
                
                <div class="form-inline" style="display: flex; justify-content: space-between;">
                    <el-form-item label="配料：" class="form-item-custom">
                        <el-input 
                            v-model="data.additiveName" 
                            placeholder="请输入配料名称"
                            clearable
                            class="custom-input"
                        >
                        </el-input>
                    </el-form-item>
                    
                    <el-form-item label="配料份数：" class="form-item-custom">
                        <el-input 
                            v-model="data.additiveNum" 
                            :min="1" 
                            :max="20"
                            controls-position="right"
                            class="custom-input"
                        />
                    </el-form-item>
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
            
            <div class="divider-custom"></div>
            
            <div class="order-result" v-if="data.orderVisible">
                <div class="result-header">
                    <span class="result-icon">🎉</span>
                    <span class="result-title">订单详情</span>
                </div>
                <div class="result-content">
                    <div class="result-item">
                        <span class="label">您所点的是：</span>
                        <span class="value description">{{ data.order.description }}</span>
                    </div>
                    <div class="result-item price-item">
                        <span class="label">总计价格是：</span>
                        <span class="value price">¥{{ data.order.cost }}</span>
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
        beverageName: '',
        additiveName: '',
        additiveNum: 1,
        order: null,
        orderVisible: false
    })

    // 提交订单
    const submitOrder = () => {
        // 验证表单
        if (!data.beverageName.trim()) {
            ElMessage.warning('请输入饮料名称')
            return
        }
        
        if (!data.additiveName.trim()) {
            ElMessage.warning('请输入配料名称')
            return
        }
        request.get('/order/processOrder', {
            params: {
                beverageName: data.beverageName,
                additiveName: data.additiveName,
                additiveNum: data.additiveNum
            }
        }).then(res => {
            if (res.code === '200') {
                data.order = res.data
                data.orderVisible = true
            } 
            else {
                ElMessage.error(res.msg)
            }
        })
    }

    // 重置表单
    const resetForm = () => {
        data.beverageName = ''
        data.additiveName = ''
        data.additiveNum = 1
        data.orderVisible = false
    }

</script>

<style scoped>

    @import "@/assets/css/main.css";


</style>