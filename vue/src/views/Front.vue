<template>
    <div>
        <div class="front-header">
            <div class="front-header-left" @click="router.push('/front/order')">
                <img src="@/assets/imgs/drink.png" alt="">
                <div class="title">售货小店</div>
            </div>
            <div class="front-header-center">
                <div style="flex: 1">
                    <el-menu :default-active="router.currentRoute.value.path" router mode="horizontal">
                        <el-menu-item index="/front/order">首页</el-menu-item>
                        <!-- <el-menu-item index="/front/coffee">精品咖啡</el-menu-item>
                        <el-menu-item index="/front/cart">购物车</el-menu-item>
                        <el-menu-item index="/front/userOrders">咖啡订单</el-menu-item>
                        <el-menu-item index="/front/scoreGoods">积分商城</el-menu-item>
                        <el-menu-item index="/front/scoreOrders">积分订单</el-menu-item> -->
                    </el-menu>
                </div>
                <!-- <div style="width: 300px; margin-right: 100px" v-if="router.currentRoute.value.path !== '/front/coffee'"> -->
                    <!-- <el-input clearable prefix-icon="Search" v-model="data.name" placeholder="搜索您想喝的咖啡" @keyup.enter="search"></el-input>
                </div> -->
            </div>

            <div class="front-header-right">
                <!-- <div v-if="!data.user.id">
                    <el-button @click="router.push('/login')">登录</el-button>
                    <el-button @click="router.push('/register')">注册</el-button>
                </div> -->
                <div>
                    <el-dropdown style="cursor: pointer; height: 60px">
                        <div style="display: flex; align-items: center; color: #eee">
                            <!-- <img v-if="data.user.avatar" style="width: 40px; height: 40px; border-radius: 50%;" :src="data.user.avatar" alt=""> -->
                            <img style="width: 40px; height: 40px; border-radius: 50%;" src="@/assets/imgs/avatar.png" alt="">
                            <!-- <span style="margin-left: 5px;">{{ data.user.name }}</span><el-icon><arrow-down /></el-icon> -->
                        </div>
                        <template #dropdown>
                            <el-dropdown-menu>
                                <el-dropdown-item @click="router.push('/front/Order')">我的地址</el-dropdown-item>
                                <!-- <el-dropdown-item @click="router.push('/front/userCollect')">我的收藏</el-dropdown-item>
                                <el-dropdown-item @click="router.push('/front/recharge')">充值记录</el-dropdown-item>
                                <el-dropdown-item @click="router.push('/front/person')">个人信息</el-dropdown-item>
                                <el-dropdown-item @click="router.push('/front/password')">修改密码</el-dropdown-item> -->
                                <!-- <el-dropdown-item @click="logout">退出登录</el-dropdown-item> -->
                            </el-dropdown-menu>
                        </template>
                    </el-dropdown>
                </div>
            </div>
        </div>
        <!-- <div class="main-body">
            <RouterView @updateUser="updateUser" />
        </div> -->
        <!-- <Footer /> -->
    </div>
</template>

<script setup>
    import router from "@/router/index.js";
    import { reactive } from "vue";
    import request from "@/utils/request.js";
//   import Footer from '@/components/Footer.vue'

    const data = reactive({
        name: null,
        // user: JSON.parse(localStorage.getItem('xm-user') || '{}'),
        top: '',
    })

    // const search = () => {
    //     router.push('/front/coffee?name=' + data.name)
    // }

    // const logout = () => {
    //     localStorage.removeItem('xm-user')
    //     router.push('/login')
    // }

    // const updateUser = () => {
    //     data.user =  JSON.parse(localStorage.getItem('xm-user') || '{}')
    // }

</script>

<style scoped>
    @import "@/assets/css/front.css";
</style>