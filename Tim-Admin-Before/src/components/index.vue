<template>
  <div id="myApp" class="myHeight" >
    <el-container>
      <el-header>
        <el-col :span="3">
          <span>admin-manage</span>
        </el-col>
        <el-col :span="8">&nbsp;</el-col>
        <el-col :span="12" align="right">
          <el-button type="info" size="mini" icon="el-icon-edit"  plain>修改密码</el-button>
          <el-button type="info" size="mini" icon="el-icon-check" @click="toLoginOut" plain>退出系统</el-button>
          <i class="el-icon-s-custom"></i><span style="font-size: 10px;">admin</span>
        </el-col>
      </el-header>
      <el-container>
      <el-aside>

        <el-menu router background-color="#2B4B6B"
                 text-color="#8398a7" active-text-color="#409EFF">
          <el-submenu index="3">

            <template slot="title">
<!--                <i class="el-icon-s-tools"></i>-->
              <span>系统菜单</span>
            </template>
            <el-submenu index="2">
              <template slot="title">
                <i class="el-icon-s-tools"></i>
                <span>用户管理</span>
              </template>
              <el-menu-item  index="/welcome" >
                <i class="el-icon-s-home"></i>
                <span slot="title">数据统计</span>
              </el-menu-item>
              <el-menu-item index="/userList">
                <i class="el-icon-user-solid"></i>
                <span slot="title">用户管理</span>
              </el-menu-item>
              <el-menu-item index="/data_count">
                <i class="el-icon-s-help"></i>
                数据统计
              </el-menu-item>
              <el-menu-item index="/excel">
                <i class="el-icon-user-solid"></i>
                <span slot="title">Excel导出</span>
              </el-menu-item>
            </el-submenu>

            <el-submenu index="1">
              <template slot="title">
                <i class="el-icon-user"></i>
                <span>人事管理</span>
              </template>
              <el-menu-item @click="openUrl('emp_list.html')">
                <i class="el-icon-s-custom"></i>
                员工档案
              </el-menu-item>
              <el-menu-item @click="openUrl('emp_info.html')">
                <i class="el-icon-s-check"></i>
                员工信息
              </el-menu-item>
            </el-submenu>

            <el-submenu index="0">
              <template slot="title">
                <i class="el-icon-user"></i>
                <span>暑假</span>
              </template>
              <el-menu-item @click="openUrl('emp_list.html')">
                <i class="el-icon-s-custom"></i>
                员工档案
              </el-menu-item>
              <el-menu-item @click="openUrl('emp_info.html')">
                <i class="el-icon-s-check"></i>
                员工信息
              </el-menu-item>
            </el-submenu>
          </el-submenu>

        </el-menu>

      </el-aside>
      <el-main>
        <router-view/>
      </el-main>
    </el-container>
    </el-container>
  </div>

</template>

<script>
import axios from 'axios'

export default {
  // name:'index'
  methods: {
    // 登出
    toLoginOut () {
      axios.get('http://localhost:8081/loginOut')
      this.$router.push('/')
    }
  }
}
</script>
<style scoped>
  .myHeight{

    margin-top: -40px;
  }
  .el-header{
    display: flex;
    background-color: #2c9999;
    color: white;
    padding-top: 20px;
    height: 100%;
    font-size: 18px;
  }
  .el-aside {
    background-color: #2B4B6B;
    color: white;
    height: 100%;
  }

  .el-main {
    color: #2c9999;
  }
  .el-container{
    height: 100%;
  }
</style>
