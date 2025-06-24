<template>
    <div class="login-container">
    <div style="width: 45%"></div>
        <div class="login-box">

            <div style="font-weight: bold; font-size: 24px; text-align: center; margin-bottom: 30px; color: #824e1c">
                <img src="@/assets/imgs/logo.png" alt="" style="width: 30px; height: 30px; position: relative; top: 5px">
                <span style="margin-left: 5px">咖啡小店</span>
            </div>
            <el-form ref="formRef" :model="data.form" :rules="data.rules">
                <el-form-item prop="username" v-if="data.adminVisible">
                    <el-input :prefix-icon="User" size="large" v-model="data.form.username" placeholder="请输入账号"></el-input>
                </el-form-item>
                <el-form-item prop="password" v-if="data.adminVisible">
                    <el-input show-password :prefix-icon="Lock" size="large" v-model="data.form.password" placeholder="请输入密码"></el-input>
                </el-form-item>
                <el-form-item >
                    <el-button size="large" type="primary" style="width: 100%" @click="login">
                        {{ data.adminVisible ? '登录' : '点餐' }}
                    </el-button>
                </el-form-item>
            </el-form>

            <el-button size="large" type="success" style="width: 100%" @click="handleAdmin">
                    {{ data.adminVisible ? '切换至用户' : '切换至管理员' }}
            </el-button>

            <div style="text-align: right; margin-top: 15px;" v-if="data.adminVisible" @click="() => ElMessage.warning('暂未开放')">
                还没有账号？请 <a style="color: #0066bc; cursor: pointer">注册</a>
            </div>

        </div>

    </div>
</template>

<script setup>
    import { reactive, ref } from "vue";
    import { User, Lock } from "@element-plus/icons-vue";
    import request from "@/utils/request.js";
    import {ElMessage} from "element-plus";
    import router from "@/router/index.js";

    const data = reactive({
        adminVisible: true,
        form: { role: 'ADMIN' },
        rules: {
            username: [
            { required: true, message: '请输入账号', trigger: 'blur' }
            ],
            password: [
            { required: true, message: '请输入密码', trigger: 'blur' }
            ]
        }
    })

    const formRef = ref()

    const login = () => {
        if(!data.adminVisible) {
            // 如果是点餐模式，则直接跳转到点餐页面
            router.push('/main')
            // router.push('/front/order')
            return;
        }
        ElMessage.warning('暂未开放');
    }

    const handleAdmin = () => {
        data.adminVisible = !data.adminVisible;
        // console.log(data.dialogVisible);
    }

</script>

<style scoped>
    .login-container {
        height: 100vh;
        overflow: hidden;
        display: flex;
        align-items: center;
        background-image: url("@/assets/imgs/BG.png");
        background-size: contain;
    }
    .login-box {
        width: 400px;
        padding: 50px 40px;
        border-radius: 5px;
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        background-color: rgba(255, 255, 255, 0.5);
        margin-left: 300px;
    }
</style>