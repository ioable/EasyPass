<template>
  <el-container>
    <el-main>
      <el-table ref="roleForm" highlight-current-row :border="true" v-loading="loading" :data="roleInfo"
        tooltip-effect="dark" style="width: 100%">
        <el-table-column prop="roleId" label="角色ID" />

        <el-table-column prop="roleName" label="角色名称" />
      </el-table>
    </el-main>
  </el-container>
</template>

<script>
import role from '@/api/role'

export default {
  name: 'RoleManage',
  data() {
    return {
      // 角色信息
      roleInfo: [],
      // 表格数据加载
      loading: true
    }
  },
  created() {
    this.getRoleInfo()
  },
  methods: {
    // 获取用户角色信息
    getRoleInfo() {
      role.getRoleInfo().then((resp) => {
        if (resp.code === 200) {
          this.roleInfo = resp.data
          this.loading = false
        } else {
          this.$notify({
            title: 'Tips',
            type: 'error',
            message: '获取信息失败',
            duration: 2000
          })
        }
      })
    }
  }
}
</script>

<style scoped lang="scss">
.el-container {
  animation: leftMoveIn .7s ease-in; //动画
}

@keyframes leftMoveIn {
  0% {
    transform: translateX(-100%); //变形
    opacity: 0;
  }

  100% {
    transform: translateX(0%);
    opacity: 1;
  }
}
</style>
