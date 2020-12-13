<template>
  <div class="container">
    <div v-if="draw">
      <md-empty-state
          md-label="Добро пожаловать!"
          md-icon="receipt_long"
      >
        <md-subheader>
          Для того чтобы начать пользоваться меню, необходимо заполнить данные профиля
        </md-subheader>
        <md-button class="md-raised md-primary" @click="goToPage('/profile')">Заполнить профиль</md-button>
      </md-empty-state>
    </div>
    <div v-else>
      <md-toolbar class="md-transparent" md-elevation="0">
        <div class="md-toolbar-row md-toolbar-offset">
          <span class="md-title">
            <p v-if="this.currentMenu.name && this.currentMenu.name !==''">{{currentMenu.name}}</p>
            <p v-else>Текущее меню</p>
          </span>
          <div class="md-toolbar-section-end">
            <md-menu>
                  <md-button class="md-icon-button md-raised md-primary" md-menu-trigger>
                    <md-icon v-if="menuNotEmpty">fiber_new</md-icon>
                    <md-icon v-else>save</md-icon>
                  </md-button>
                  <md-menu-content>
                    <md-menu-item v-if="!menuNotEmpty" @click="openChangeDialog(true)">Сохранить текущее</md-menu-item>
                    <md-menu-item @click="generateNew">Новое меню</md-menu-item>
                  </md-menu-content>
                </md-menu>
          </div>
        </div>
        <MenuEditDialog :selectedMenu="currentMenu" v-if="showEditDialog" @close="openChangeDialog(false)" @menuUpdateSuccess="replaceUpdated"/>
        <div class="md-toolbar-row md-toolbar-offset">
          <span class="md-subheader">
            <p v-if="this.currentMenu.description && this.currentMenu.description !==''">{{currentMenu.description}}</p>
            <p v-else>Если вам нравится текущее меню, вы можете сохранить его на будущее</p>
          </span>
        </div>
      </md-toolbar>
      <md-divider/>
      <div>
        <div class="md-layout item-content">
          <MealCard class="item md-layout-item" v-for="mealItem in currentMenu.mealsInMenu" v-bind:key="mealItem.id" :parentData="mealItem" />
        </div>
        <md-divider/>
      </div>
    </div>
  </div>
</template>

<script>
import MealCard from "@/components/MealCard";
import MenuEditDialog from "@/components/MenuEditDialog";
export default {
  name: 'currentMenuBoard',
  components:{MealCard, MenuEditDialog},
  data() {
    return {
      content: '',
      draw: true,
      showEditDialog: false
    };
  },
  computed: {
    currentUser() {
      return this.$store.state.auth.user;
    },
    currentProfile(){
      return this.$store.state.profileModule.profile;
    },
    currentMenu(){
      return this.$store.state.currentMenuModule.currentMenu;
    },
    menuNotEmpty(){
      return this.currentMenu.name && this.currentMenu.name !=='' && this.currentMenu.description && this.currentMenu.description !== '';
    }
  },
  methods:{
    goToPage: function(page){
      this.$router.push(page);
    },
    openChangeDialog(state){
      this.showEditDialog = state;
    },
    generateNew(){
      this.$store.dispatch('currentMenuModule/generateNew', this.currentUser.id);
    },
    statsEmpty(){
      return this.currentProfile.dailyProteins <= 0 ||
          this.currentProfile.dailyFats <= 0 ||
          this.currentProfile.dailyCarbohydrates <= 0 ||
          this.currentProfile.dailyCalories <= 0;
    },
    replaceUpdated(menu){
      this.$store.dispatch('currentMenuModule/updCurrent', menu);
      this.openChangeDialog(false);
    }
  },
  beforeMount() {
    this.draw = this.statsEmpty();
    this.$store.dispatch('currentMenuModule/getCurrentMenu', this.currentUser.id);
  },
  mounted() {
    if(!this.currentUser) {
      this.goToPage('/login');
    }
  }
};
</script>
<style scoped>
.container{
  margin-top: 5%;
  align-content: center;
}
.item{
  max-width: 400px;
}
.item-content{}
</style>