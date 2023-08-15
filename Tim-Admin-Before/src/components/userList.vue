<template>
  <el-card>
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/welcome' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>系统管理</el-breadcrumb-item>
      <el-breadcrumb-item>用户管理</el-breadcrumb-item>
    </el-breadcrumb>

      <el-row :gutter="10">
        <el-col :span="6">
          <el-input placeholder="请输入内容" v-model="search" class="input-with-select">
            <el-button slot="append" icon="el-icon-search" @click="searchdata"></el-button>
          </el-input>
        </el-col>
        <el-col :span="6">
          <el-button type="primary" @click="adduserdialog = true">添加用户</el-button>
        </el-col>
      </el-row>

    <!--表单模块-->
      <el-table :data="users" >
        <el-table-column prop="username" label="用户名" ></el-table-column>
        <el-table-column prop="password" label="密码"> </el-table-column>
        <el-table-column label="操作">
          <template slot-scope="scope">
            <!-- 修改 -->
            <el-button type="primary" icon="el-icon-edit" plain size="mini" @click="openupdate(scope.row)"></el-button>
            <!-- 删除 -->
            <el-button type="danger" icon="el-icon-delete" plain size="mini" @click="userDelete(scope.row.userId)"></el-button>
            <el-button type="success" size="mini">角色管理</el-button>
          </template>
        </el-table-column>
      </el-table>
      <el-pagination
        background
        layout="prev, pager, next"
        :total="total"
        :page-size="pagesize"
        :current-page.sync="curpage"
        @current-change="changecur"
      >
      </el-pagination>

      <!-- 弹出的添加模态框-->
      <el-dialog title="添加" :visible.sync="adduserdialog" @close="clearuseradd">
        <el-form :model="adduserform" :rules="addrules" ref="addform">
          <el-form-item prop="username" label="用户名" :label-width="formLabelWidth">
            <el-input v-model="adduserform.username" auto-complete="off"></el-input>
          </el-form-item>
          <el-form-item prop="password" label="密码" :label-width="formLabelWidth">
            <el-input type="password" v-model="adduserform.password" auto-complete="off"></el-input>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="adduserdialog = false">取 消</el-button>
          <el-button type="primary" @click="addUser">确 定</el-button>
        </div>
      </el-dialog>
      <!-- 编辑-->
      <el-dialog title="编辑" :visible.sync="updateuserdialog" @close="closeupdate">
        <el-form :model="updateuserform" :rules="updateuserrules" ref="updateform">
          <el-form-item prop="username" label="用户名" :label-width="formLabelWidth">
            <el-input v-model="updateuserform.username" auto-complete="off" disabled></el-input>
          </el-form-item>
          <el-form-item prop="password" label="密码" :label-width="formLabelWidth">
            <el-input v-model="updateuserform.password" auto-complete="off"></el-input>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="updateuserdialog = false">取 消</el-button>
          <el-button type="primary" @click="updateUser">确 定</el-button>
        </div>
      </el-dialog>
  </el-card>

</template>

<script>

// 导入axios
import axios from 'axios'

export default {
  name: 'userList',
  data () {
    return {
      users: [], // 表格数据
      curpage: 1, // 当前页
      pagesize: 2, // 每页显示条数
      total: 0, // 总条数
      search: '', // 模糊查询条件
      adduserdialog: false,
      adduserform: {// 绑定添加模态框数据
        username: '',
        password: ''
      },
      formLabelWidth: '120px', // 添加模态框中文本框宽度
      // 设置添加模态框的规则
      addrules: {
        username: [
          { required: true, message: '用户名为必填项', trigger: 'blur' }
          // { min: 3, max: 10, message: '长度在 3 到 10 个字符', trigger: 'blur' }
        ],
        password: [
          { required: true, message: '密码为必填项', trigger: 'blur' }
        ]
        /* 自由发挥其他字段 */
      },
      updateuserdialog: false, // 修改模态框
      updateuserform: {// 修改模态框中的绑定数据
        username: '',
        password: ''
      },
      updateuserrules: {}// 自由发挥修改规则
    }
  },
  // 一加载页面就 立即执行！
  mounted () {
    this.userList()
  },
  methods: {
    // 分页查询函数
    userList () {
      var mythis = this
      axios.get(
        'http://localhost:8081/user/selectUserByPageLikeUsername',
        {params: {
          current: mythis.curpage,
          rows: mythis.pagesize,
          search: mythis.search
        }}
      ).then((res) => {
        console.log(res)
        mythis.users = res.data.data.list// 将后端返回数据渲染在表格中
        mythis.total = res.data.data.total
      })
    },
    // 改变页面时，当前页值发生变化，重新调用分页查询函数
    changecur (curpage) {
      this.userList(curpage)
    },
    searchdata () {
      this.curpage = 1// 模糊查询时初始页面为首页
      this.userList()// 回调分页查询函数
      this.search = ''
    },
    // 添加用户的请求
    addUser () {
      var mythis = this
      // 先通过引用判断验证规则
      this.$refs.addform.validate((valid) => {
        // 判断验证结果
        if (valid) {
          // 发送请求
          axios({
            headers: {'Content-Type': 'application/json;charset=utf-8'},
            method: 'post',
            url: 'http://localhost:8081/user/insertUser',
            data: JSON.stringify(mythis.adduserform)
          }).then((res) => {
            // 结果后端返回结果值，判断状态码
            // eslint-disable-next-line eqeqeq
            if (res.data.code == 200) {
              // 发送成功消息提示
              this.$message({
                message: res.data.msg,
                type: 'success'
              })
              this.adduserdialog = false// 关闭模态框
              this.userList()// 更新添加后的数据
            } else {
              this.$message.error(res.data.msg)// 添加失败消息提示
            }
          })
        } else {
          console.log('添加失败')
          return false
        }
      })
    },
    // 关闭添加用户的模态框
    clearuseradd () {
      this.$refs.addform.resetFields()// 重置form表单
    },
    // 打开修改模态框，---数据回显！
    openupdate (data) {
      console.log(data)
      this.updateuserdialog = true// 打开模态框
      this.updateuserform = data// 数据回显
    },
    // 发送修改请求
    updateUser () {
      var mythis = this
      axios({
        headers: {'Content-Type': 'application/json;charset=utf-8'},
        method: 'put',
        url: 'http://localhost:8081/user/updateUser',
        data: JSON.stringify(mythis.updateuserform)
      }).then((res) => {
        // 结果后端返回结果值，判断状态码
        // eslint-disable-next-line eqeqeq
        if (res.data.code == 200) {
          // 发送成功消息提示
          this.$message({
            message: res.data.msg,
            type: 'success'
          })
          this.updateuserdialog = false// 关闭模态框
          this.userList(this.curpage = 1)// 更新添加后的数据
        } else {
          this.$message.error(res.data.msg)// 修改失败消息提示
        }
      })
    },
    closeupdate () {
      this.$refs.updateform.resetFields()// 重置修改form表单
    },
    // 删除用户
    userDelete (id) {
      // 确认弹框提示
      this.$confirm('是否删除该用户?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        // 发送axios请求删除数据
        axios.delete(
          'http://localhost:8081/user/deleteUser',
          {params: {userId: id}}
        ).then((res) => {
          // eslint-disable-next-line eqeqeq
          if (res.data.code == 200) {
            this.$message({
              type: 'success',
              message: res.data.msg
            })
            // 回到首页
            this.curpage = 1
            this.userList()// 刷新删除后的数据
          } else {
            this.$message.error(res.data.msg)
          }
        })
      }).catch(() => {
        // 取消删除后的消息提示
        this.$message({
          type: 'info',
          message: '取消删除'
        })
      })
    }
  }
}

</script>

<style>
  .el-breadcrumb{
    padding-bottom: 20px;
  }
  .el-pagination{
    padding-top: 20px;
  }
</style>
