<template>
  <el-container>

      <el-row>
        <div id="img1" style="width: 800px;height:400px;"></div>
        <div id="img2" style="width: 800px;height:400px;"></div>
      </el-row>

      <el-row>
        <div id="img3" style="width: 800px;height:400px;"></div>
        <div id="img4" style="width: 800px;height:400px;"></div>
      </el-row>

  </el-container>
</template>

<script>
import statistic from '@/api/statistic'

export default {
  name: 'StatisticOverview',
  data () {
    return {
      //考试名称
      examNames: [],
      //考试通过率
      passRate: [],
      //饼图的数据
      pieData: [],
    }
  },
  created () {
    //页面数据加载的等待状态栏
    this.loading = this.$Loading.service({
      body: true,
      lock: true,
      text: '数据拼命加载中,(*╹▽╹*)',
      spinner: 'el-icon-loading',
    })
    this.getExamPassRate()
    this.getExamNumbers()
  },
  methods: {
    async getExamPassRate () {
      await statistic.getExamPassRate().then((resp) => {
        if (resp.code === 200) {
          this.examNames = resp.data[0].split(',')
          this.passRate = resp.data[1].split(',')
          this.drawLine()
          this.drawBrokenLine()
          this.drawImg4()
          this.loading.close()
        }
      })
    },
    //考试通过率柱状图  
    drawLine () {
      // 基于准备好的dom，初始化echarts实例
      let myChart = this.$echarts.init(document.getElementById('img1'))
      // 绘制图表
      myChart.setOption({
        title: {
          text: '考试通过率',
          subtext: 'dashbord1',
          x: 'center',
          y: 'top',
          textAlign: 'center'
        },
        tooltip: {},
        xAxis: {
          data: this.examNames
        },
        yAxis: {},
        series: [{
          name: '通过率',
          type: 'bar',
          data: this.passRate
        }]
      })
    },
    //获取考试次数数据
    async getExamNumbers () {
      await statistic.getExamNumbers().then((resp) => {
        console.log(resp)
        let examNames = resp.data[0].split(',')
        let examNumbers = resp.data[1].split(',')
        examNames.forEach((item, index) => {
          this.pieData.push({
            name: item,
            value: parseInt(examNumbers[index])
          })
        })
        this.drawPie()
      })
    },
    // this.examNames 和 this.passRate 分别是 x 轴和柱状图的数据，这里应该是在组件中定义的。
    //考试次数饼图
    drawPie () {
      // 基于准备好的dom，初始化echarts实例
      let myChart = this.$echarts.init(document.getElementById('img2'))
      //初始化一个 ECharts 实例，并指定要显示的 DOM 元素
      myChart.setOption({//设置图表的标题、提示框、图例和数据等。
        title: {
          text: '考试次数占比',
          subtext: 'dashbord2',
          x: 'center'
        },
        tooltip: {//tooltip 属性表示鼠标悬停在图表上时显示的提示框
          trigger: 'item',
          formatter: '{a} <br/>{b} : {c}次 ({d}%)'
        },
        legend: {
          orient: 'vertical',
          left: 'left',
          data: this.pieData
        },
        series: [
          {
            name: '考试次数',
            type: 'pie',
            radius: '55%',
            data: this.pieData,
            roseType: 'angle',
            itemStyle: {
              normal: {
                shadowBlur: 200,
                shadowColor: 'rgba(0, 0, 0, 0.5)'
              }
            }
          }
        ]
      })
    },
    //通过率的折线图 给折线图填充颜色，从而形成面积图
    drawBrokenLine () {
      //初始化ehcharts实例
      let myChart = this.$echarts.init(document.getElementById('img3'))
      //指定图表的配置项和数据
      var option = {
        //标题
        title: {
          text: '考试通过率折线图',
          x: 'center'
        },
        //x轴
        xAxis: {
          data: this.examNames
        },
        //y轴没有显式设置，根据值自动生成y轴
        yAxis: {},
        //数据-data是最终要显示的数据
        series: [{
          name: '通过率',
          type: 'line',
          areaStyle: {
            normal: {}
          },
          data: this.passRate
        }],
      }
      //使用刚刚指定的配置项和数据项显示图表
      myChart.setOption(option)
    },
    drawImg4 () {  //去掉x轴两端的留白。
      let myChart = this.$echarts.init(document.getElementById('img4'))
      myChart.setOption({
        color: ['#cd5c5c'],
        textStyle: {
          color: 'black'
        },
        tooltip: {
          trigger: 'axis',
          axisPointer: {
            type: 'shadow'
          },
          formatter: '{a} <br/>{b} : {c}'
        },

        grid: {
          containLabel: true
        },
        xAxis: {
          type: 'value',
          boundaryGap: [0, 0.01],
          axisLine: {
            lineStyle: {
              color: '#fff'
            },
          },
          'axisLabel': {
            'interval': 0,
            fontSize: 18,
            formatter: '{value}',
          }
        },
        yAxis: {
          axisLine: {
            lineStyle: {
              color: '#fff'
            },
          },
          'axisLabel': {
            'interval': 0,
            fontSize: 18,
          },
          type: 'category',
          data: this.examNames
        },
        series: [{
          name: '通过率：',
          type: 'bar',
          data: this.passRate,
        }]
      })
    }
  }
}
</script>

<style scoped lang="scss">

.el-container {
  width: 100%;
  height: 100%;
}

.el-container {
  animation: leftMoveIn .7s ease-in;
}

@keyframes leftMoveIn {
  0% {
    transform: translateX(-100%);
    opacity: 0;
  }
  100% {
    transform: translateX(0%);
    opacity: 1;
  }
}
</style>
