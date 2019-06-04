<template>
    <div style="height: 100%;">
      <menu-type-a v-if="type === 'A'" @change="change"></menu-type-a>
      <menu-type-b v-if="type === 'B'" @change="change"></menu-type-b>
    </div>
</template>

<script>
import menuTypeA from '@/views/systemManage/menuManage/menu-type-a'
import menuTypeB from '@/views/systemManage/menuManage/menu-type-b'
export default {
  name: 'menuManage',
  components: {
    menuTypeA, menuTypeB
  },
  data () {
    return {
      type: 'A'
    }
  },
  methods: {
    change () {
      this.type = this.type === 'A' ? 'B' : 'A'
    }
  }
}
</script>

<style scoped>
</style>
<style>
</style>
