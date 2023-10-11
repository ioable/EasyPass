<template>
  <el-scrollbar :native="false" style="height: 100%">
    <div>
      <h1 class="title">
        模拟考试系统
      </h1>

      <el-row>
        <el-col :span="15" :offset="5">

          <div style="margin-left: 2em">
            <h4>导航栏</h4>

            <h4>1.在线考试</h4>
            <p>学生用户在注册登录之后，可以在本系统进行在线的考试，考试可由教师和管理员进行布置并设置考试权限（公开，密码），考试题型分为
              单选、多选、判断、简答题，并支持题目附带配图。考试过程中需开启摄像头进行考试，系统会自动抓拍考生实时考试状态。</p>

            <h4>2.成绩模块</h4>
            <p>
              参加考试后的学生用户，在提交试卷后进入考试结果页面，页面会自动核对学生用户的逻辑题的对错，对于简答题需要老师或者超级管理员进行批阅。对于学生用户参与的考试，学生用户可以查看到考试的详情并可以查看到自己所错的逻辑题。
            </p>

            <h4>3.题库模块</h4>
            <p>学生用户在题库模块中可以进行题目的功能训练，训练模式分为，顺序练习，随机练习，也可以根据题型练习（单选，多选，判断）。用户答题时会实时判断正确与否，并有错题解析功能。</p>

            <h4>4.考试管理</h4>
            <p>
              教师和系统管理员用户有权限对系统存在的考试进行操作，本系统考试支持公开考试和密码口令考试，并可以对考试进行禁用也可以在设置考试时间段，对于考试可以进行很便利的进行组卷，系统内置两种组卷模式，题库组卷和自由选题组卷。
            </p>


          </div>
        </el-col>
      </el-row>

    </div>

  </el-scrollbar>
</template>

<!-- <script>
  export default {
    name: 'Dashboard',
    created () {
      // 调用父组件Main的展示系统公告方法
      this.$emit('showSystemNotice')
    },
  }
</script> -->

<style scoped lang="scss">
div {
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

.title {
  text-align: center;
  font-size: 25px;
}


p {
  text-indent: 2em;
}

a {
  text-decoration: none
}</style>
