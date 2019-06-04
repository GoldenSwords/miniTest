<template>
    <div style="color: white">
      <Row>
        <Col :lg="{ span: 18, offset: 2 }" style="font-size: 30px">管理系统</Col>
        <Col :lg="{ span: 2, offset: 2 }">
          <Dropdown @on-click="pathT()">
            <Icon type="ios-contact" size="60"/>
            <DropdownMenu slot="list">
              <DropdownItem>个人信息</DropdownItem>
              <DropdownItem divided>退出</DropdownItem>
            </DropdownMenu>
          </Dropdown>
        </Col>
      </Row>
    </div>
</template>

<script>
export default {
  name: 'BooksHeader',
  methods: {
    pathT () {
      this.$router.push('/LayoutManage')
    }
  }
}
</script>

<style scoped>

</style>
