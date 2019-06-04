<template>
    <div>
      login
    </div>
</template>

<script>
import { getMenuByUser } from '@/api/menu'
export default {
  name: 'login',
  data () {
    return {
    }
  },
  mounted () {
    getMenuByUser().then(resp => {
      console.log(resp)
    })
  }
}
</script>

<style scoped>

</style>
