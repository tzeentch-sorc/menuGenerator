<template>
  <div>
    <md-dialog :md-active.sync="showDialog">
      <div class="wrapper">
        <div class="left_block">
           <img :src="mealItem.picture" class="picture" alt=""/>
        </div>
        <div class="right_block">
          <md-dialog-title>
            {{mealItem.name}}
          </md-dialog-title>
          <md-dialog-content>
            <md-tabs>
              <md-tab md-label="БЖУ">
                <MealStatsTable :mealItem="mealItem"/>
              </md-tab>
              <md-tab md-label="Порции">
                <md-table>
                  <md-table-row>
                    <md-table-cell>Порций</md-table-cell>
                    <md-table-cell>{{additional.portions}} шт</md-table-cell>
                  </md-table-row>
                  <md-table-row>
                    <md-table-cell>Калорий в порции</md-table-cell>
                    <md-table-cell>{{additional.calories}} кал</md-table-cell>
                  </md-table-row>
                  <md-table-row>
                    <md-table-cell>Вес порции</md-table-cell>
                    <md-table-cell>{{additional.weight}} г</md-table-cell>
                  </md-table-row>
                </md-table>
              </md-tab>
            </md-tabs>
          </md-dialog-content>
        </div>
      </div>
      <md-divider/>
      <md-dialog-content class="md-scrollbar">
        <md-tabs>
          <md-tab md-label="Ингредиенты">
            <IngredientTable :item="additional.mealProducts"/>
          </md-tab>
          <md-tab md-label="Рецепт">
            <div class="recipe" v-for="str in additional.recipe" v-bind:key="str"> {{str}} </div>
          </md-tab>
        </md-tabs>
      </md-dialog-content>
      <md-dialog-actions>
        <md-button v-if="currentUser && !buttonDelete" class="md-primary" @click="add(mealItem.id, currentUser.id, false)">В избранное</md-button>
        <md-button v-if="currentUser && !buttonDelete" class="md-primary" @click="add(mealItem.id, currentUser.id, true)">В черный список</md-button>
        <md-button v-if="currentUser && buttonDelete" class="md-primary" @click="remove(mealItem.id, currentUser.id)">Удалить из списка</md-button>
        <md-button class="md-primary md-accent" @click="closeInfo(null)">Закрыть</md-button>
      </md-dialog-actions>
    </md-dialog>
    <md-snackbar md-position="center" md-duration="5000" :md-active.sync="showSnackbar" md-persistent>
      <span>{{result}}</span>
      <md-button class="md-primary" @click="showSnackbar = false">Закрыть</md-button>
    </md-snackbar>
  </div>
</template>

<script>
import MealStatsTable from "@/components/MealStatsTable";
import IngredientTable from "@/components/IngredientTable";
import PreferencesService from "@/services/preferences.service";
export default {
  name: "MealInfo",
  components: {IngredientTable, MealStatsTable},
  props:{
    mealItem: Object,
    additional: Object,
    buttonDelete: Boolean
  },
  computed:{
    currentUser() {
      return this.$store.state.auth.user;
    }
  },
  data() {
    return {
      showDialog: false,
      showSnackbar: false,
      result: ''
    };
  },
  mounted() {
    this.showDialog = true;
  },
  methods: {
    closeInfo: function(arg){
      this.$emit('closeInfo', arg);
      this.showDialog = false;
      this.showSnackbar = false;
    },
    add(mealId, userId, ban){
      PreferencesService.addPref(userId, mealId, ban).then(resp =>
          {
            if(resp && resp.data.message){
              this.result = resp.data.message;
            } else {
              this.result = ban ? "Добавлено в черный список!" : "Добавлено в избранное!";
            }
            this.showSnackbar = true;
          }
      ).catch(
          err => {
            this.result = err.response.data.message;
            this.showSnackbar = true;
          }
      );
    },
    remove(mealId, userId){
      PreferencesService.deletePref(mealId, userId).then(resp => {
        if(resp && resp.data.message){
          this.result = resp.data.message;
        } else {
          this.result ="Удалено!"
        }
        this.closeInfo({})
      });
    }
  }
};
</script>

<style lang="scss" scoped>
.md-dialog /deep/.md-dialog-container {
  max-width: 1000px;
  min-width: 700px;
  padding: 10px;
  max-height: 700px;
  min-height: 300px;
}
.picture{
  width: 100%;
}

.wrapper{
  width: 100%;
}

.left_block { 
  float:left;
  width: 40%;
  padding: 10px;
  margin-top: 10px;
}
.right_block { 
  float:right;
  width:60%;
  padding: 10px;
}
.recipe{
  margin: 10px;
  max-height: 300px;
}

.md-dialog-title{
  margin-bottom: 10px;
}
</style>