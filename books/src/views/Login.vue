<template>
  <div class="fill" id="container" style="width: 100%;height: 100%;">
    <div class="output" id="output" style="width: 100%;height: 100%;">
      <div class="box-card">
        <el-form>
          <el-row class="masked bounceIn animated">
           <span class="title">
             VueDemo 工程 <sub>一个亿的大项目</sub>
           </span>
          </el-row>
          <el-row :gutter="10">
            <el-col :span="10">
              <el-form-item>
                <el-input placeholder="用户名" v-model="username" class="login">
                  <template slot="prepend">
                    <!--<i class="el-icon-phone"></i>-->
                    <svg class="icon" aria-hidden="true">
                      <use xlink:href="#iconyonghu1"></use>
                    </svg>
                  </template>
                </el-input>
              </el-form-item>
            </el-col>
            <el-col :span="10">
              <el-form-item>
                <el-input placeholder="密码" v-model="pwd" type="password" class="login">
                  <template slot="prepend">
                    <!--<i class="el-icon-view"></i>-->
                    <svg class="icon" aria-hidden="true">
                      <use xlink:href="#iconmima1"></use>
                    </svg>
                  </template>
                </el-input>
              </el-form-item>
            </el-col>
            <el-col :span="4">
              <el-form-item style="text-align: center;">
                <el-button @click="loginIn" type="success" size="medium">Login</el-button>
              </el-form-item>
            </el-col>
          </el-row>
        </el-form>
      </div>
    </div>
  </div>
</template>

<script>
import vector from '@/plugins/util/vector'
import {login} from '@/api/auth'
import store from '@/store/index'
export default {
  name: 'Login',
  data () {
    return {
      username: 'admin',
      pwd: 'admin'
    }
  },
  methods: {
    loginIn () {
      login().then(resp => {
        if (resp.data.code === 200) {
          store.commit('USER_LOGIN', resp.data.data)
          store.commit('USER_LOGIN_RIGHTS', store.state.menu.tree)
          this.$router.push('/home')
        } else {
          alert(false)
        }
      })
    }
  },
  beforeDestroy () {
    this.layer.destory()
  },
  mounted () {
    this.layer = vector.install()
    this.layer.Victor('container', 'output')
  }
}
</script>

<style scoped>
  .text {
    font-size: 14px;
  }

  .item {
    padding: 18px 0;
  }

  .box-card {

    /*height: 300px;*/
    text-align: center;
    position: absolute;
    top: 50%;
    left: 50%;
    width: 500px;
    margin: -56px 0 0 -250px;
    border-radius: 3px;
  }
  .el-form{
    /*padding: 10px;*/
    /*border-radius: 5px;*/
    /* border: 1px solid rgba(40,40,40,0.8); */
    /*box-shadow: 0 0 5px rgba(40,40,40,0.8);*/
  }
  .el-form-item{
    margin: 0;
  }
  .masked .title{
    font-size: 20px;
    font-weight: bold;
  }
  .masked sub{
    font-size: 14px;
    margin-left: 10px;
  }
  .masked{
    display: block;
    padding: 20px;
    /*width: 600px;*/
    /*height: 100px;*/
    /*渐变背景*/
    background-image: -webkit-linear-gradient(left, #3498db, #f47920 10%, #d71345 20%, #f7acbc 30%,
    #ffd400 40%, #3498db 50%, #f47920 60%, #d71345 70%, #f7acbc 80%, #ffd400 90%, #3498db);
    color: transparent; /*文字填充色为透明*/
    -webkit-text-fill-color: transparent;
    -webkit-background-clip: text;          /*背景剪裁为文字，只将文字显示为背景*/
    background-size: 200% 100%;            /*背景图片向水平方向扩大一倍，这样background-position才有移动与变化的空间*/
    /* 动画 */
    animation: masked-animation 4s infinite linear;
  }
  @keyframes masked-animation {
    0% {
      background-position: 0 0;   /*background-position 属性设置背景图像的起始位置。*/
    }
    100% {
      background-position: -100% 0;
    }
  }
</style>
<style>
  .login .el-input__inner{
    background-color: rgba(100,100,100,0.4);
    border: 0;
    color: white;
  }
  .login .el-input-group__prepend{
    background-color: rgba(100,100,100,0.4);
    border: 0;
    color: white;
  }
  .icon{
    width: 20px;
    height: 20px;
  }
</style>
