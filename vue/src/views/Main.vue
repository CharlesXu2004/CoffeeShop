<template>

    <div class="front-header">
            <div class="front-header-left">
                <img src="@/assets/imgs/drink.png" alt="">
                <div class="title">售货机</div>
            </div>
            <div class="front-header-center">
                <div style="flex: 1">
                    <el-menu router mode="horizontal">
                        <el-menu-item style="color: #eee; border: none ;" @click="comingSoon">首页</el-menu-item>
                        <el-menu-item style="color: #eee; border: none ;" @click="handleOrderClick">点单界面</el-menu-item>
                        <el-menu-item style="color: #eee; border: none ;" @click="handleHistoryClick">我的订单</el-menu-item>
                        <el-menu-item style="color: #eee; border: none ;" @click="comingSoon">积分商城</el-menu-item>
                        <el-menu-item style="color: #eee; border: none ;" @click="comingSoon">积分明细</el-menu-item>
                    </el-menu>
                </div>
                <div style="width: 300px; margin-right: 100px">
                    <el-input clearable prefix-icon="Search" v-model="data.searchData" placeholder="搜索您想喝的"></el-input>
                </div>
            </div>

            <div class="front-header-right">
                <div>
                    <el-dropdown style="cursor: pointer; height: 60px">
                        <div style="display: flex; align-items: center; color: #eee">
                            <img style="width: 40px; height: 40px; border-radius: 50%;" src="@/assets/imgs/avatar.png" alt="">
                        </div>
                        <template #dropdown>
                            <el-dropdown-menu>
                                <!-- <el-dropdown-item >我的地址</el-dropdown-item> -->
                                <!-- <el-dropdown-item >我的收藏</el-dropdown-item> -->
                                <!-- <el-dropdown-item >充值记录</el-dropdown-item> -->
                                <el-dropdown-item @click="comingSoon">个人信息</el-dropdown-item>
                                <!-- <el-dropdown-item >修改密码</el-dropdown-item> -->
                                <!-- <el-dropdown-item >退出登录</el-dropdown-item> -->
                            </el-dropdown-menu>
                        </template>
                    </el-dropdown>
                </div>
            </div>
        </div>
    
    <div class="beverage-order">

        <div class="background-decoration">
            <div class="floating-circle circle-1"></div>
            <div class="floating-circle circle-2"></div>
        </div>
        
        <el-card class="order-card" style="margin-top: 20px;" v-if="data.orderVisible">
            
            <div style="margin-top: 10px; margin-bottom: 30px; display: flex; justify-content: center; ">
                <span class="header-text">点单界面</span>
            </div>
            
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

                    <el-table :data="data.order.goods" style="width: 100%" show-summary :summary-method="getSummaries">
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

        <el-card class="order-card" style="margin-top: 20px;" v-if="data.historyVisible">

            <div style="margin-top: 10px; margin-bottom: 30px; display: flex; justify-content: center; ">
                <span class="header-text">我的订单</span>
            </div>

            <el-table :data="data.historyData" style="width: 100%">
                <el-table-column label="订单号" align="center">
                    <template #default="scope">
                        <span>{{ scope.row.id }}</span>
                    </template>
                </el-table-column>
                <el-table-column label="订单描述" align="center">
                    <template #default="scope">
                        <span>{{ scope.row.description }}</span>
                    </template>
                </el-table-column>
                <el-table-column label="金额" align="center">
                    <template #default="scope">
                        <span>¥ {{ scope.row.cost }}</span>
                    </template>
                </el-table-column>
            </el-table>

            <div v-if="data.historyPageInfo.total" style="margin-top: 20px; justify-content: center; display: flex;">
                <el-pagination @current-change="loadHistory" layout="total, prev, pager, next, jumper" :page-size="data.historyPageInfo.pageSize" v-model:current-page="data.historyPageInfo.pageNum" :total="data.historyPageInfo.total" :background="background"/>
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
        order: null,
        orderVisible: true,
        historyVisible: false,
        historyData: null,
        historyPageInfo: {
            pageNum: 1,
            pageSize: 10,
            total: 0
        },
        searchData: null
    })


    const handleOrderClick = () => {
        data.orderVisible = true
        data.historyVisible = false
    }

    const handleHistoryClick = () => {
        data.orderVisible = false
        data.historyVisible = true
        loadHistory(data.historyPageInfo.pageNum)
    }

    const loadHistory = (pageNum) => {
        request.get('/order/selectPage', {
            params: {
                pageNum: pageNum,
                pageSize: data.historyPageInfo.pageSize
            }
        }).then(res => {
            if (res.code === '200') {
                data.historyData = res.data.list
                data.historyPageInfo.total = res.data.total
            } 
            else {
                ElMessage.error(res.msg)
            }
        })
    }

    const comingSoon = () => {
        ElMessage.warning('暂未开放')
    }

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
                if (data.order.cost > 0) {
                    saveOrder(res.data)
                }
                else {
                    data.order.goods = null
                    ElMessage.warning('非法订单，请检查饮品和配料')
                }
            } 
            else {
                ElMessage.error(res.msg)
            }
        })

        // 恢复最后一个配料行
        data.json.additives.push(lastAdditive)
    }

    const saveOrder = (order) => {
        request.post('/order/add', order).then(res => {
            if (res.code === '200') {
                ElMessage.success('订单已保存')
            } 
            else {
                ElMessage.error(res.msg)
            }
        })
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
    @import "@/assets/css/front.css";


</style>