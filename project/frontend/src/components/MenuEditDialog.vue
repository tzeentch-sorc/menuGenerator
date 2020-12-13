<template>
  <div>
    <md-dialog :md-active.sync="showDialog">
      <md-dialog-title>Редактирование меню</md-dialog-title>
      <md-content>
        <md-field :class="messageName">
          <label>Название меню</label>
          <md-input v-model="nameField" maxlength="25" required/>
          <span class="md-helper-text">Введите имя для меню</span>
          <span class="md-error">Поле не должно быть пустым</span>
        </md-field>

        <md-field :class="messageDesc">
          <label>Описание</label>
          <md-textarea v-model="description" md-counter="200" required/>
          <span class="md-helper-text">Задайте описание для меню</span>
          <span class="md-error">Поле не должно быть пустым</span>
        </md-field>

      </md-content>
      <md-dialog-actions>
        <md-button class="md-primary" @click="saveMenu">Сохранить</md-button>
        <md-button class="md-accent" @click="closeEdit">Закрыть</md-button>
      </md-dialog-actions>
    </md-dialog>
  </div>
</template>
<script>
import MenuService from '@/services/menu.service'

export default {
  name: 'MenuEditDialog',
  props:{
    selectedMenu: {type:  Object, default: () =>({})}
  },
  computed: {
    currentUser() {
      return this.$store.state.auth.user;
    },
    currentProfile() {
      return this.$store.state.profileModule.profile;
    },
    messageName () {
      return {
        'md-invalid': !this.isNameOk
      }
    },
    messageDesc () {
      return {
        'md-invalid': !this.isDescOk
      }
    },
  },
  data: () => ({
    showDialog: false,
    nameField: null,
    description: null,
    isNameOk: false,
    isDescOk: false
  }),
  mounted() {
    this.showDialog = true
    if(this.selectedMenu !== {}){
      this.nameField = this.selectedMenu.name;
      this.description = this.selectedMenu.description;
      this.isDescOk = true;
      this.isNameOk = true;
    }
  },
  methods:{
    closeEdit(){
      this.showDialog = false;
      this.$emit('close', false);
    },
    checkFields(){
      this.isNameOk = !(this.nameField === '' || this.nameField === null);
      this.isDescOk = !(this.description === '' || this.description === null);
      return this.nameField && this.description;
    },
    saveMenu(){
      if(!this.checkFields()) return;
      let params = new URLSearchParams([["name", this.nameField], ["description", this.description], ["id", this.selectedMenu.id]]);
      MenuService.update(params).then(
          resp =>
          {
            this.$emit('menuUpdateSuccess', resp.data)
          }
      ).catch(
          err => {
            //TODO error handling
          }
      )
    },
    createMenu(){

    }
  }
}
</script>
<style lang="scss" scoped>
.md-dialog /deep/.md-dialog-container{
  max-width: 700px;
  min-width: 500px;
  padding: 20px;
}
</style>