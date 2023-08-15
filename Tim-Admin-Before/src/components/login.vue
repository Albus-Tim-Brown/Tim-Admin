<template>
  <div class="loginbody">
    <div class="logindata">
      <div class="logintext">
        <h2>Welcome</h2>
      </div>
      <!-- 表单 -->
      <el-form :model="userInfo" :rules="rules" ref="loginForm" label-width="0"  class="login_form">
        <el-form-item label="用户名" prop="username">
          <!-- 用户名-->
          <el-input v-model="userInfo.username" clearable placeholder="username" prefix-icon="el-icon-user"></el-input>
        </el-form-item>
        <!-- 密码-->
        <el-form-item prop="password">
          <el-input v-model="userInfo.password" prefix-icon="el-icon-lock" placeholder="password" type="password"></el-input>
        </el-form-item>
      </el-form>
      <div class="butt">
        <el-button type="primary" :plain="true" @click="login">登录</el-button>
        <el-button class="shou" @click="toRegister">注册</el-button>
      </div>
    </div>
  </div>
</template>

<script>
// 导入axios
import axios from 'axios'

export default {
  name: 'login',
  // 表单数据
  data () {
    return {
      // 绑定数据对象
      userInfo: {
        username: '',
        password: ''
      },
      // 校验规则
      rules: {
        username: [
          { required: true, message: '请输入用户名', trigger: 'blur' },
          { min: 3, max: 10, message: '长度在 3 到 10 个字符', trigger: 'blur' }
        ],
        password: [
          { required: true, message: '请输入密码', trigger: 'blur' }
        ]
      }
    }
  },
  methods: {
    toRegister () {
      this.$router.replace('/register')
    },
    login () {
      axios({
        headers: {'Content-Type': 'application/json;charset=utf-8'}, // JSON请求
        method: 'Post',
        url: 'http://localhost:8081/login',
        data: JSON.stringify(this.userInfo)
      }).then((res) => {
        // eslint-disable-next-line eqeqeq
        if (res.data.code == 200) {
          // 消息提示
          this.$message({
            showClose: true,
            message: res.data.msg,
            type: 'success'
          })
          // 跳转到主页
          this.$router.replace('/index')
        } else {
          this.$message({
            showClose: true,
            message: res.data.msg,
            type: 'error'
          })
          // 重置表单
          this.$refs.loginForm.resetFields()
        }
      })
    }
  }
}
</script>
<style scoped>
  .loginbody {
    margin-top: -50px;
    width: 100%;
    height: 100%;
    min-width: 1000px;
    background-image: url("../assets/login.jpeg");
    /*background-color: cadetblue;*/
    background-size: 100% 100%;
    background-position: center center;
    overflow: auto;
    background-repeat: no-repeat;
    position: fixed;
    line-height: 100%;
    padding-top: 150px;
  }

  .logintext {
    margin-bottom: 20px;
    line-height: 50px;
    text-align: center;
    font-size: 30px;
    font-weight: bolder;
    color: white;
    text-shadow: 2px 2px 4px #000000;
  }

  .logindata {
    width: 400px;
    height: 300px;
    transform: translate(-50%);
    margin-left: 50%;
  }

  .tool {
    display: flex;
    justify-content: space-between;
    color: #606266;
  }

  .butt {
    margin-top: 10px;
    text-align: center;
  }
</style>
