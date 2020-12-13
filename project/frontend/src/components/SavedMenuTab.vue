<template>
  <div>
    <md-toolbar class="md-transparent" md-elevation="0">
      <div class="md-toolbar-section-start">
        <span class="md-title">Ваши меню</span>
      </div>
      <div v-if="menuList.length" class="md-toolbar-section-end">
        <md-field  md-clearable>
          <md-input placeholder="Искать по имени" v-model="searchName"/>
        </md-field>
        <md-button class="md-icon-button md-primary md-raised" @click="search">
          <md-icon>search</md-icon>
        </md-button>
      </div>

    </md-toolbar>
    <div v-if="menuList.length">
      <md-toolbar class="md-transparent" md-elevation="0">
        <div class="md-toolbar-section-start md-layout md-gutter">
          <div class="md-layout-item">
            <span>Имя</span>
          </div>
          <div class="md-layout-item">
            <span>Описание</span>
          </div>
          <div class="md-layout-item">
            <span>Блюд</span>
          </div>
        </div>
      </md-toolbar>
      <md-divider/>
      <div class="md-toolbar item-div" v-for="item in menuList" :key="item.id">
        <div v-if="showMealList !== item.id" class="md-toolbar-row md-toolbar-section-start md-layout md-gutter row" @click="openMealsList(item)">
          <div class="md-layout-item">
            <span>{{ item.name }}</span>
          </div>
          <div class="md-layout-item">
            <span class="description">{{ item.description }}</span>
          </div>
          <div class="md-layout-item">
            <span>{{ item.mealsLength }}</span>
          </div>
          <hr/>
        </div>
        <div v-else>
          <md-toolbar class="md-transparent" md-elevation="0">
            <div class="md-toolbar-row">
              <div class="md-toolbar-section-start">
                <h4>{{selected.name}}</h4>
              </div>
              <div class="md-toolbar-section-end">
                <md-menu>
                  <md-button class="md-icon-button" md-menu-trigger>
                    <md-icon>edit</md-icon>
                  </md-button>
                  <md-menu-content>
                    <md-menu-item @click="openChangeDialog(true)">Изменить</md-menu-item>
                    <md-menu-item @click="deleteMenuFromList(selected.id)">Удалить</md-menu-item>
                    <md-menu-item @click="setCurrent(selected)">Сделать текущим</md-menu-item>
                  </md-menu-content>
                </md-menu>
              </div>
            </div>
            <div class="md-toolbar-row">
              <span>{{selected.description}}</span>
            </div>
          </md-toolbar>
          <div class="md-layout item-content">
            <MealCard class="item md-layout-item" v-for="mealItem in selected.mealsInMenu" v-bind:key="mealItem.id" :parentData="mealItem" />
          </div>
        </div>
      </div>
      <div v-if="menuList.length" v-observe-visibility="handleBottom">
        <md-progress-bar v-if="this.hasNextPage" md-mode="indeterminate"/>
      </div>
      <MenuEditDialog v-if="showEditDialog" :selectedMenu="selected" @menuUpdateSuccess="replaceUpdated" @close="openChangeDialog"/>
    </div>
    <div v-else>
      <md-empty-state
          md-label="Меню не найдены"
          md-icon="no_food"
          md-description="Возможно, вы еще не сохранили ни одного"
      >
      </md-empty-state>
    </div>
  </div>
</template>
<script>
import MealCard from "@/components/MealCard";
import MenuEditDialog from "@/components/MenuEditDialog";
import MenuService from "@/services/menu.service"

export default {
  name: 'SavedMenuTab',
  components: {MenuEditDialog, MealCard},
  computed: {
    currentUser() {
      return this.$store.state.auth.user;
    },
    currentProfile() {
      return this.$store.state.profileModule.profile;
    }
  },
  data: () => ({
    showMealList: -1,
    selected: {},
    showEditDialog: false,
    hasNextPage: false,
    nextPage: 0,
    menuList: [],
    searchName: ''
  }),
  mounted() {
    this.getUserMenus(null);
  },
  methods:{
    getUserMenus(search){
      MenuService.getAll(this.nextPage, this.currentUser.id, search).then(
          response => {
            this.menuList.push(...response.data);
            this.hasNextPage = response.headers['x-has-next-page'] ==='true';
          }
      );
    },
    search(){
      this.hasNextPage = false;
      this.nextPage = 0;
      this.menuList = [];
      this.selected = {};
      this.showMealList = -1;
      this.getUserMenus(this.searchName);
    },
    deleteMenuFromList(id){
      MenuService.deleteById(id).then(
        response => {
          this.menuList = this.menuList.filter((el)=>{
            return el.id!==id})})
      .catch(err => {
        alert("Failed to delete")
      })
      //TODO error handling
    },
    setCurrent(item){
      const us_id = this.currentUser.id;
      this.$store.dispatch('currentMenuModule/setCurrentMenu', {
        newCurrentItem: item,
        userId: us_id})
        .then(
          response => {
            let item = this.menuList.findIndex(x => x.id === this.showMealList);
            this.selected = {};
            this.showMealList = -1;
            if(response !== null && !response.message){
              this.menuList[item] = response;
            } else {
              this.menuList.splice(item, 1);
            }

        }
      )
    },
    openChangeDialog(state){
      this.showEditDialog = state;
    },
    openMealsList(item){
      this.showMealList = item.id;
      MenuService.getById(item.id).then(
          response => {
            this.selected = response.data;
          }
      )
    },
    replaceUpdated(menu){
      let item = this.menuList.find(x => x.id === menu.id);
      item.name = menu.name;
      item.description = menu.description;
      this.selected = item;
      this.openChangeDialog(false);
    },
    handleBottom: function (isVisible){
      if(!isVisible) { return; }
      if(!this.hasNextPage) return;
      this.nextPage++
      this.getUserMenus(this.searchName);
    }
  }
}
</script>
<style scoped>
.description{
  display: block;
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
  max-width: 15ch;
}
.row:hover{
  background-color: lightgrey;
}
.item{
  max-width: 260px;
}
.item-div{
  border-bottom: lightgrey 1px solid;
}
</style>