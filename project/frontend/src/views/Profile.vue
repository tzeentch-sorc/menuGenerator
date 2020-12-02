<template>
  <div style="margin-top: 1%">
    <div v-if="currentUser">
      <md-app>
        <md-app-drawer md-permanent="full">
          <md-toolbar class="md-transparent" md-elevation="0">
            Страницы
          </md-toolbar>

          <md-list>
            <md-list-item @click="setState(0)">
              <md-icon>recent_actors</md-icon>
              <span class="md-list-item-text">Параметры</span>
            </md-list-item>

            <md-list-item @click="setState(1)">
              <md-icon>rule</md-icon>
              <span class="md-list-item-text">Черный список</span>
            </md-list-item>

            <md-list-item @click="setState(2)">
              <md-icon>favorite</md-icon>
              <span class="md-list-item-text">Сохраненные меню</span>
            </md-list-item>
          </md-list>
        </md-app-drawer>
        <md-app-content>
          <ParamTab v-if="curState === 0"/>
        </md-app-content>
      </md-app>
    </div>

  </div>
</template>

<script>
import ParamTab from "@/components/ParamTab";
export default {
  name: 'Profile',
  components: {ParamTab},
  computed: {
    currentUser() {
      return this.$store.state.auth.user;
    },
    currentProfile(){
      return this.$store.state.profileModule.profile;
    }
  },
  data(){
    return {
      noDetails: true,
      curState: 0
    }
  },
  mounted() {
    if (!this.currentUser) {
      this.$router.push('/login');
    }
  },
  methods: {
    setState(state){
      this.curState = state;
    }
  }
};
</script>
<style lang="scss" scoped>
.container{
  margin-top: 5%;
  max-height: 80%;
  min-height: 50%;
  max-width: 55%;
}
.md-app-drawer{
  max-width: 25%;
}
</style>