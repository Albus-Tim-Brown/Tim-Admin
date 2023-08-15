<template>
<!--  <el-button type="primary" @click="excelWrite()">导出用户</el-button>-->
  <el-button type="primary" @click="excel">导出用户数据excel(使用EasyExcel)</el-button>
</template>

<script>
import axios from 'axios'

export default {
  name: 'excel',
  methods: {
    excelWrite () {
      axios({
        method: 'get',
        url: 'http://localhost:8081/excel/excelWrite',
        responseType: 'blob'
      }).then((res) => {
        console.log(res)
        // 得到请求到的数据后，对数据进行处理
        let blob = new Blob([res.data], {type: 'application/vnd.ms-excel;charset=utf-8'})// 创建一个类文件对象：Blob对象表示一个不可变的、原始数据的类文件对象
        let fileName = decodeURI(res.headers['content-disposition'])// 设置文件名称,decodeURI：可以对后端使用encodeURI() 函数编码过的 URI 进行解码。encodeURI() 是后端为了解决中文乱码问题
        console.log(fileName)
        if (fileName) { // 根据后端返回的数据处理文件名称
          fileName = fileName.substring(fileName.indexOf('=') + 1)
        }
        const link = document.createElement('a')// 创建一个a标签
        link.download = fileName// 设置a标签的下载属性
        link.style.display = 'none'// 将a标签设置为隐藏
        link.href = URL.createObjectURL(blob)// 把之前处理好的地址赋给a标签的href
        document.body.appendChild(link)// 将a标签添加到body中
        link.click()// 执行a标签的点击方法
        URL.revokeObjectURL(link.href) // 下载完成释放URL 对象
        document.body.removeChild(link)// 移除a标签
      })
    },
    excel () {
      axios({
        method: 'get',
        url: 'http://localhost:8081/excel/excel1',
        responseType: 'blob'
      }).then((res) => {
        console.log(res)
        const link = document.createElement('a')
        let blob = new Blob([res.data], {type: 'multipary/form-data'})
        link.style.display = 'none'
        link.href = URL.createObjectURL(blob)
        link.setAttribute('download', decodeURI(Date.now() + '导出模板.xlsx'))
        document.body.appendChild(link)
        link.click()
        document.body.removeChild(link)
        console.log(res)
      })
    }
  }
}
</script>

<style scoped>

</style>
