<template>
  <el-card>
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/welcome' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>数据管理</el-breadcrumb-item>
      <el-breadcrumb-item>销售信息</el-breadcrumb-item>
    </el-breadcrumb>

    <!--    <el-row :gutter="10">-->
    <!--      <el-col :span="6">-->
    <!--        <el-input placeholder="请输入内容" v-model="search" class="input-with-select">-->
    <!--          <el-button slot="append" icon="el-icon-search" @click="searchdata"></el-button>-->
    <!--        </el-input>-->
    <!--      </el-col>-->
    <!--      <el-col :span="6">-->
    <!--        <el-button type="primary" @click="adduserdialog = true">添加用户</el-button>-->
    <!--      </el-col>-->
    <!--    </el-row>-->

    <!--表单模块-->
    <el-table :data="sales">
      <el-table-column prop="dealNum" label="成交订单量"></el-table-column>
      <el-table-column prop="refundNum" label="退款订单量"></el-table-column>
      <el-table-column prop="browseNum" label="浏览量"></el-table-column>
      <el-table-column prop="addpurchaseNum" label="加购量"></el-table-column>
      <el-table-column prop="preorderNum" label="预购量"></el-table-column>
      <el-table-column label="操作">
        <template slot-scope="scope">
          <!-- 修改 -->
          <el-button type="primary" icon="el-icon-edit" plain size="mini" @click="openupdate(scope.row)"></el-button>
          <!-- 删除 -->
<!--          <el-button type="danger" icon="el-icon-delete" plain size="mini"-->
<!--                     @click="userDelete(scope.row.userId)"></el-button>-->
<!--          <el-button type="success" size="mini">角色管理</el-button>-->
        </template>
      </el-table-column>
    </el-table>
    <!--    <el-pagination-->
    <!--      background-->
    <!--      layout="prev, pager, next"-->
    <!--      :total="total"-->
    <!--      :page-size="pagesize"-->
    <!--      :current-page.sync="curpage"-->
    <!--      @current-change="changecur"-->
    <!--    >-->
    <!--    </el-pagination>-->
    <!--    &lt;!&ndash; 弹出的添加模态框&ndash;&gt;-->
    <!--    <el-dialog title="添加" :visible.sync="adduserdialog" @close="clearuseradd">-->
    <!--      <el-form :model="adduserform" :rules="addrules" ref="addform">-->
    <!--        <el-form-item prop="username" label="用户名" :label-width="formLabelWidth">-->
    <!--          <el-input v-model="adduserform.username" auto-complete="off"></el-input>-->
    <!--        </el-form-item>-->
    <!--        <el-form-item prop="password" label="密码" :label-width="formLabelWidth">-->
    <!--          <el-input type="password" v-model="adduserform.password" auto-complete="off"></el-input>-->
    <!--        </el-form-item>-->
    <!--      </el-form>-->
    <!--      <div slot="footer" class="dialog-footer">-->
    <!--        <el-button @click="adduserdialog = false">取 消</el-button>-->
    <!--        <el-button type="primary" @click="addUser">确 定</el-button>-->
    <!--      </div>-->
    <!--    </el-dialog>-->
    <!-- 编辑-->
    <el-dialog title="编辑" :visible.sync="updateSaleDialog" @close="closeupdate">
      <el-form :model="updateSaleForm" :rules="updateSalerules" ref="updatesaleform">
        <el-form-item prop="username" label="用户名" :label-width="formLabelWidth">
          <el-input v-model="updateSaleForm.dealNum" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item prop="password" label="密码" :label-width="formLabelWidth">
          <el-input v-model="updateSaleForm.password" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item prop="password" label="密码" :label-width="formLabelWidth">
          <el-input v-model="updateSaleForm.password" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item prop="password" label="密码" :label-width="formLabelWidth">
          <el-input v-model="updateSaleForm.password" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item prop="password" label="密码" :label-width="formLabelWidth">
          <el-input v-model="updateSaleForm.password" auto-complete="off"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="updateSaleDialog = false">取 消</el-button>
        <el-button type="primary" @click="updateSale">确 定</el-button>
      </div>
    </el-dialog>
  </el-card>
</template>

<script setup>
import axios from 'axios'

export default {
  name: 'sale',
  data () {
    return {
      sales: [],
      formLabelWidth: '120px',
      updateSaleDialog: false,
      updateSaleForm: {
        dealNum: '',
        refundNum: '',
        browseNum: '',
        addpurchaseNum: '',
        preorderNum: ''
      },
      updateSaleRules: {
      }
    }
  },
  mounted () {
    this.saleList()
  },
  methods: {
    saleList () {
      axios.get(
        'http://localhost:8081/sale'
      ).then((res) => {
        console.log(res)
        this.sales = res.data.data
      })
    },
    openupdate (data) {
      this.updateSaleDialog = true
      this.updateSaleForm = data
    },
    closeupdate () {
      this.$refs.updatesaleform.resetFields()
    },
    updateSale () {
      axios({
        headers: {'Content-Type': 'application/json;charset=utf-8'},
        method: 'post',
        url: 'http://localhost:8081/sale',
        data: JSON.stringify(this.updateSaleForm)
      }).then((res) => {
        // 结果后端返回结果值，判断状态码
        if (res.data.code === 200) {
          // 发送成功消息提示
          this.$message({
            message: res.data.msg,
            type: 'success'
          })
          this.updateSaleDialog = false// 关闭模态框
          this.saleList()// 更新添加后的数据
        } else {
          this.$message.error(res.data.msg)// 修改失败消息提示
        }
      })
    }
  }
}
</script>

<style scoped>

</style>
