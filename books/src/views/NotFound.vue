<template>
    <div class="NF">
      <table>
        <tr>
          <td>
            <svg class="icon" aria-hidden="true">
              <use xlink:href="#iconmima"></use>
            </svg>
            <span class="tips"> 页面不见了</span>
          </td>
        </tr>
      </table>
    </div>
</template>

<script>
export default {
  name: 'NotFound'
}
</script>

<style scoped>
  .NF,.NF table{
    width: 100%;
    height: 100%;
  }
  .NF .icon {
    width: 120px;
    height: 120px;
    vertical-align: middle;
  }
  .tips{
    color: #4e8cee;
    font-size: 50px;
    font-family: 黑体;
  }
</style>
