<template>
  <div class="layout">
    <Layout>
      <Header>
        <BooksHeader></BooksHeader>
      </Header>
      <Content>
        <Navs :data="navs"></Navs>
      </Content>
      <Footer>
        <BooksFoot></BooksFoot>
      </Footer>
    </Layout>
  </div>
</template>

<script>
import BooksHeader from '@/components/BooksHeader'
import BooksFoot from '@/components/BooksFoot'
import Navs from '@/components/Navs'
// import { formatRoutes } from '@/util/utils'
import { booksAll, bookType } from '@/api/book' // booksById
// import { navType } from '@/api/nav'
export default {
  name: 'HelloWorld',
  components: {
    BooksFoot, BooksHeader, Navs
  },
  data () {
    return {
      books: '',
      navs: []
    }
  },
  mounted () {
    booksAll().then(resp => {
      this.books = resp.data
    })
    // navType('menu').then(resp => {
    //   this.$router.addRoutes(formatRoutes(resp.data))
    // })
    bookType().then(resp => {
      this.navs = resp.data
    })
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h1, h2 {
  font-weight: normal;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  display: inline-block;
  margin: 0 10px;
}
a {
  color: #42b983;
}
.layout,.ivu-layout{
  width: 100%;
  height: 100%;
}
.ivu-layout-content{
  height: calc(100% - (64px + 69px));
}
.ivu-layout-header{
  background-color: #aa530e !important;
}
.ivu-layout-footer{
  background-color: #aa530e !important;
}
</style>
