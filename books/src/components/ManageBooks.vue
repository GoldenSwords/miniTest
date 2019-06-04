<template>
    <div class="box">
      <Button type="success" @click="doSave">应用</Button>
      <template v-for="(item, index) in books">
        <Row :gutter="0" :key="index">
          <template v-for="(items, indexs) in item">
            <Col span="6" :key="indexs">
              <Card :bordered="false" :key="indexs">
                <Input slot="title" prefix="ios-contact" placeholder="Enter name" v-model="items.name" style="width: auto" />
                <InputNumber v-model="items.hotpoint" style="width: 100%"></InputNumber>
                <Input v-model="items.desc" type="textarea" :rows="4" placeholder="作品介绍" />
                <DatePicker type="datetime" v-model="items.time" :value="new Date(items.time)" placeholder="上架时间" style="width: 200px"></DatePicker>
                <div></div>
              </Card>
            </Col>
          </template>
        </Row>
      </template>
    </div>
</template>

<script>
import { booksAll } from '@/api/book' // booksById
export default {
  name: 'ManageBooks',
  props: {
    data: Array
  },
  data () {
    return {
      books: []
    }
  },
  mounted () {
    this.getBooks()
  },
  methods: {
    splits (text) {
      return text.length > 30 ? text.substring(0, 30) + '...' : text
    },
    setColor (val) {
      return val < 10 ? '#09DDF3' : val < 20 ? '#06F4DD' : val < 30 ? '#2DFABD' : val < 40 ? '#AFF8AF' : val < 50 ? '#E8E796' : val < 60 ? '#F9BE72' : val < 70 ? '#FBAB92' : val < 80 ? '#FD88A8' : val < 90 ? '#FE5E8C' : val < 100 ? '#FF596B' : '#FF3044'
    },
    sSort (bk) {
      let temp = []
      for (let i = 0; i < bk.length; i++) {
        if ((i + 1) % this.lineNumber === 0) {
          temp.push(bk[i])
          this.books.push(temp)
          temp = []
        } else {
          temp.push(bk[i])
        }
      }
      if (temp.length > 0) {
        this.books.push(temp)
      }
    },
    doSave () {
      console.log(this.$store.state.books)
      // this.$store.state.books = resp.data
    },
    getBooks () {
      booksAll().then(resp => {
        this.$store.state.books = resp.data
        const book = resp.data
        book.sort((a, b) => {
          return b.hotpoint - a.hotpoint
        })
        this.sSort(book)
      })
    }
  }
}
</script>

<style scoped>
  .ivu-col,.ivu-col-span-6{
    padding: 6px !important;
  }
  .box{
    background-color: #eee;
  }
</style>
