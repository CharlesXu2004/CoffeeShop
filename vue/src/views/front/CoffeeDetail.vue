<template>
    <div style="width: 60%; margin: 0 auto; padding: 20px 0; min-height: 100vh">
        <div class="card" style="margin-bottom: 20px; padding: 20px">
            <div style="display: flex; grid-gap: 20px">
                <img :src="data.coffee.img" alt="" style="width: 300px; height: 300px; border-radius: 5px">
                <div style="flex: 1">
                <div style="margin-bottom: 10px; display: flex; align-items: center">
                    <div style="font-size: 24px; flex: 1">{{ data.coffee.name }}</div>
                    <div style="margin-right: 20px; color: red">
                    <img src="@/assets/imgs/hot.png" alt="" style="width: 16px; position: relative; top: 2px">{{ data.coffee.readCount }}
                    </div>
                    <span @click="collect" style="color: orange; cursor: pointer" v-if="data.coffee.userCollect"><el-icon size="16" style="position: relative; top: 2px"><StarFilled /></el-icon>取消收藏</span>
                    <span @click="collect" style="color: #666; cursor: pointer" v-else><el-icon size="16" style="position: relative; top: 2px"><Star /></el-icon>收藏</span>
                </div>
                <div style="margin-bottom: 20px; padding: 15px; border-radius: 5px; background-color: #333; color: goldenrod">{{ data.coffee.description }}</div>
                <div style="margin: 15px 0; border-radius: 5px; color: #666; text-align: justify; font-size: 13px">{{ data.coffee.descr }}</div>
                <div style="margin-bottom: 10px"><span style="color: #666">原料：</span>{{  data.coffee.material }}</div>
                <div style="margin-bottom: 20px"><span style="color: #666">已售：</span>{{  data.coffee.saleCount }} 份</div>
                <div style="margin-bottom: 20px">
                    <b style="font-size: 25px; color: red">￥{{ data.coffee.price }}</b>
                </div>
                <div style="margin-bottom: 20px">
                    <el-input-number :min="1" size="large" style="width: 150px" v-model="data.num"></el-input-number>
                    <el-button style="margin-left: 5px" type="primary" size="large" @click="handleCart">加入购物车</el-button>
                    <el-button style="margin-left: 5px" type="danger" size="large" @click="handleBuy">立即下单</el-button>
                </div>
                <div style="font-size: 12px; color: #666">友情提示：1元=1积分，消费后获得的积分可以个人信息页面查看</div>
                </div>
            </div>
        </div>

        <div class="card" style="padding: 20px; margin-bottom: 20px">
        <div style="margin-bottom: 30px; font-size: 20px; padding-bottom: 10px; border-bottom: 1px solid #ddd;">
            <span @click="changeTab('商品详情')" :class="{ 'active' : data.active === '商品详情' }" style="cursor: pointer">商品详情</span>
            <span @click="changeTab('商品评价')" :class="{ 'active' : data.active === '商品评价' }" style="margin-left: 30px; cursor: pointer">商品评价</span>
        </div>
        <div style="line-height: 24px" v-if="data.active === '商品详情'">
            <div v-html="data.coffee.content"></div>
        </div>

        <div style="line-height: 24px" v-if="data.active === '商品评价'">
            <div v-if="data.total">
            <div v-for="item in data.commentList" :key="item.id">
                <div style="display: flex; grid-gap: 20px; margin-bottom: 15px">
                <img style="width: 50px; height: 50px; border-radius: 50%" :src="item.userAvatar" alt="">
                <div style="flex: 1; border-bottom: 1px solid #ddd; padding-bottom: 15px;">
                    <div style="margin-bottom: 5px; display: flex; align-items: center">
                    <span style="margin-right: 10px">{{ item.userName }}</span>
                    <el-rate v-model="item.score" show-score allow-half disabled></el-rate>
                    </div>
                    <div style="margin-bottom: 10px; color: #888; font-size: 12px">{{ item.time }}</div>
                    <div>{{ item.content }}</div>
                </div>
                </div>
            </div>
            <div v-if="data.total" style="margin-top: 20px">
                <el-pagination @current-change="load" layout="total, prev, pager, next" :page-size="data.pageSize" v-model:current-page="data.pageNum" :total="data.total" />
            </div>
            </div>
            <div v-else style="padding: 30px; text-align: center; font-size: 20px; color: #666">暂无评价...</div>
        </div>
        </div>

        <el-dialog title="咖啡下单" v-model="data.formVisible" width="40%" :close-on-click-modal="false" destroy-on-close>
        <el-form :model="data.form" :rules="data.rules" label-width="100px"  style="padding: 20px 30px" ref="formRef">
            <el-form-item label="配送类型" prop="type">
            <el-radio-group v-model="data.form.type">
                <el-radio-button value="堂食" label="堂食"></el-radio-button>
                <el-radio-button value="外送" label="外送"></el-radio-button>
            </el-radio-group>
            </el-form-item>
            <el-form-item label="收货地址" prop="addressId" v-if="data.form.type === '外送'">
            <el-select style="width: 100%" v-model="data.form.addressId">
                <el-option v-for="item in data.addressList" :key="item.id" :value="item.id" :label="item.name + ' | ' + item.phone + ' | ' + item.address"></el-option>
            </el-select>
            </el-form-item>
            <el-form-item label="购买数量" prop="num">
            <el-input-number style="width: 150px" :min="1" v-model="data.num" placeholder="请输入数量"></el-input-number>
            </el-form-item>
            <el-form-item label="商家" prop="businessId">
            <el-select style="width: 100%" v-model="data.form.businessId">
                <el-option v-for="item in data.businessList" :key="item.id" :value="item.id" :label="item.name"></el-option>
            </el-select>
            </el-form-item>
        </el-form>
        <template #footer>
            <span class="dialog-footer">
            <el-button @click="data.formVisible = false">取消</el-button>
            <el-button type="primary" @click="add">确定</el-button>
            </span>
        </template>
        </el-dialog>
    </div>
</template>

<script setup>
import { reactive } from "vue";
import request from "@/utils/request.js";
import router from "@/router/index.js";
import {ElMessage} from "element-plus";

import { ref } from "vue"
const formRef = ref()

const data = reactive({
  user: JSON.parse(localStorage.getItem('xm-user') || '{}'),
  id: router.currentRoute.value.query.id,
  active: '商品详情',
  coffee: {},
  form: {},
  pageNum: 1,
  pageSize: 5,
  num: 1,
  addressId: null,
  businessId: null,
  commentList: [],
  businessList: [],
  addressList: [],
  formVisible: false,
  rules: {
    businessId: [
      { required: true, message: '请选择商家', trigger: 'change' }
    ],
    type: [
      { required: true, message: '请选择配送类型', trigger: 'change' }
    ],
    num: [
      {  required: true, message: '请输入数量', trigger: 'blur' }
    ],
  }
})

const handleCart = () => {
  request.post('/cart/add', { userId: data.user.id, coffeeId: data.id, num: data.num }).then(res => {
    if (res.code === '200') {
      ElMessage.success("加入成功")
    } else {
      ElMessage.error(res.msg)
    }
  })
}

const handleBuy = () => {
  data.form = { num: data.num, type: '堂食'}
  data.formVisible = true

  request.get('/business/selectAll', {
    params: {
      identify: '是'
    }
  }).then(res => {
    data.businessList = res.data
  })
  request.get('/address/selectAll', {
    params: {
      userId: data.user.id
    }
  }).then(res => {
    data.addressList = res.data
  })
}

const changeTab = (name) => {
  data.active = name
}

const loadComment = () => {
  request.get('/comment/selectPage', {
    params: {
      pageNum: data.pageNum,
      pageSize: data.pageSize,
      coffeeId: data.id
    }
  }).then(res => {
    if (res.code === '200') {
      data.commentList = res.data?.list || []
      data.total = res.data?.total
    }
  })
}
loadComment()

const collect = () => {
  request.post('/collect/add', { userId: data.user.id, coffeeId: data.id }).then(res => {
    if (res.code === '200') {
      ElMessage.success("操作成功")
      load()
    } else {
      ElMessage.error(res.msg)
    }
  })
}

const load = () => {
  request.get('/coffee/selectById/' + data.id).then(res => {
    data.coffee = res.data
  })
}
load()

const add = () => {
  formRef.value.validate((valid) => {
    if (valid) {
      if (data.form.type === '外送' && !data.form.addressId) {
        ElMessage.warning('请选择地址')
        return
      }
      data.form.cartList = [ { coffeeId: data.id, userId: data.user.id, num: data.num } ]
      request.post('/orders/add', data.form).then(res => {
        if (res.code === '200') {
          data.formVisible = false
          ElMessage.success('下单成功')
          load()
        } else {
          ElMessage.error(res.msg)
        }
      })
    }
  })
}
</script>

<style>
.active {
  color: #faa200;
}
</style>