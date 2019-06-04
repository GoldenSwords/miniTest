<template>
  <Layout>
    <Header>Header</Header>
    <Layout>
      <Sider hide-trigger>
        <template v-for="(item, index) in menu">
          <div :key="index">
            <template v-if="item.leaf">
              <div>{{ item.name }}</div>
            </template>
            <template v-else>
              <router-link :to="item.path">{{ item.name }}</router-link>
            </template>
            <template v-if="item.children" v-for="(items, indexs) in item.children">
              <div :key="indexs">
                <router-link :to="items.path">{{ items.name }}</router-link>
              </div>
            </template>
          </div>
        </template>
      </Sider>
      <Content><router-view></router-view></Content>
    </Layout>
    <Footer>Footer</Footer>
  </Layout>
</template>

<script>
import { navType } from '@/api/nav'
// import { formatRoutes } from '@/util/utils'
export default {
  name: 'LayoutManage',
  data () {
    return {
      menu: []
    }
  },
  mounted () {
    navType('back').then(resp => {
      this.menu = resp.data
    })
  }
}
</script>

<style scoped>

</style>
