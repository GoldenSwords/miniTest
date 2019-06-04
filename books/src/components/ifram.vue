<template>
  <iframe v-if="$route.meta.src" :src='$route.meta.src' class="iframe" ref="iframe" style="width: 100%;height: 100%;border: 0;overflow: hidden;"></iframe>
</template>

<script>
export default {
  name: 'ifram',
  mounted () {
    console.log(this.$route)
  }
}
</script>

<style scoped>

</style>
