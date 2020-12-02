<template>
  <div class="profile">
    <div>
        <span class="md-title">
          Параметры профиля
        </span>
    </div>
      <div v-if="!isRedacting" style="margin-left: 10px">
        <md-table style="margin-top: 5%">
          <md-table-row>
            <md-table-cell>Рост</md-table-cell>
            <md-table-cell>{{currentProfile.height}}</md-table-cell>
          </md-table-row>
          <md-table-row>
            <md-table-cell>Вес</md-table-cell>
            <md-table-cell>{{currentProfile.weight}}</md-table-cell>
          </md-table-row>
          <md-table-row>
            <md-table-cell>Возраст</md-table-cell>
            <md-table-cell>{{currentProfile.age}}</md-table-cell>
          </md-table-row>
          <md-table-row>
            <md-table-cell>Пол</md-table-cell>
            <md-table-cell>
              <span v-if="currentProfile.male">Мужской</span>
              <span v-else-if="!currentProfile.male">Женский</span>
              <span v-else>Не указан</span>
            </md-table-cell>
          </md-table-row>
          <md-table-row>
            <md-table-cell>Активность</md-table-cell>
            <md-table-cell>{{this.getActivity(currentProfile.activity)}}</md-table-cell>
          </md-table-row>
        </md-table>
        <md-divider/>
        <div v-if="currentProfile">
          <div style="margin: 7% 0 3% 0">
        <span class="md-title">
          Рассчитанная ежедневная норма
        </span>
          </div>
          <md-table>
            <md-table-row>
              <md-table-cell>Белки, г</md-table-cell>
              <md-table-cell>{{currentProfile.dailyProteins}}</md-table-cell>
            </md-table-row>
            <md-table-row>
              <md-table-cell>Жиры, г</md-table-cell>
              <md-table-cell>{{currentProfile.dailyFats}}</md-table-cell>
            </md-table-row>
            <md-table-row>
              <md-table-cell>Углвеоды, г</md-table-cell>
              <md-table-cell>{{currentProfile.dailyCarbohydrates}}</md-table-cell>
            </md-table-row>
            <md-table-row>
              <md-table-cell>Калории</md-table-cell>
              <md-table-cell>{{currentProfile.dailyCalories}}</md-table-cell>
            </md-table-row>
          </md-table>
        </div>

        <div class="md-layout md-alignment-center-right">
          <md-button v-if="!isRedacting" @click="change" class="md-raised md-primary md-layout-item md-size-20">
            Изменить
          </md-button>
        </div>
      </div>
      <div v-else>
        <div class="md-layout md-gutter">
          <div class="md-layout-item md-small-size-100">
            <md-field>
              <label for="height">Рост, см</label>
              <md-input type="text" id="height" name="height" v-model="profile.height"/>
            </md-field>
          </div>

          <div class="md-layout-item md-small-size-100">
            <md-field>
              <label for="weight">Вес, кг</label>
              <md-input type="text" id="weight" name="weight" v-model="profile.weight"/>
            </md-field>
          </div>
        </div>
          <div class="md-layout md-gutter">
            <div class="md-layout-item md-small-size-100">
              <md-field>
                <label for="gender">Пол</label>
                <md-select name="gender" id="gender" v-model="profile.male">
                  <md-option :value="true">Мужской</md-option>
                  <md-option :value="false">Женский</md-option>
                </md-select>
                <span class="md-error">The gender is required</span>
              </md-field>
            </div>

            <div class="md-layout-item md-small-size-100">
              <md-field>
                <label for="age">Возраст (полных лет)</label>
                <md-input type="number" id="age" name="age" v-model="profile.age"/>
              </md-field>
            </div>
          </div>
          <md-field>
            <label>Активность</label>
            <md-select v-model="profile.activity">
              <md-option :value="coefs[0]">{{this.activities[0]}}</md-option>
              <md-option :value="coefs[1]">{{this.activities[1]}}</md-option>
              <md-option :value="coefs[2]">{{this.activities[2]}}</md-option>
              <md-option :value="coefs[3]">{{this.activities[3]}}</md-option>
              <md-option :value="coefs[4]">{{this.activities[4]}}</md-option>
            </md-select>
          </md-field>
        <div class="md-layout md-alignment-center-right">
          <md-button @click="setNewProfile" class="md-raised md-primary md-layout-item md-size-20">
            Сохранить
          </md-button>
          <md-button @click="redact(false)" class="md-raised md-primary md-layout-item md-size-20">
            Отмена
          </md-button>
        </div>

      </div>
  </div>
</template>
<script>
import ProfileShort from "@/models/profileShort";

export default {
  name: 'ParamTab',

  computed:{
    currentUser() {
      return this.$store.state.auth.user;
    },
    currentProfile(){
      return this.$store.state.profileModule.profile;
    }
  },
  data: () => ({
    activities:[
      'Не занимаюсь спортом',
      'Иногда занимаюсь спортом',
      'Регулярные занятия спортом',
      'Ежедневные тренировки',
      'Многоразовые ежедневные тренировки'
    ],
    coefs:[1.2, 1.375, 1.55, 1.7, 1.9],
    profile: new ProfileShort('', '', '', '', '', ''),
    isRedacting: false
  }),
  methods: {
    getActivity(coef){
      switch (coef){
        case this.coefs[4]: return this.activities[4];
        case this.coefs[3]: return this.activities[3];
        case this.coefs[2]: return this.activities[2];
        case this.coefs[1]: return this.activities[1];
        case this.coefs[0]: return this.activities[0];
      }
    },
    redact(state){
      this.isRedacting = state;
    },
    setNewProfile(){
      const id = this.currentUser.id;
      this.$store.dispatch('profileModule/setProfile', {
        newProfile: this.profile,
        userId: id
      }).then(
          () => {
            this.redact(false);
          }
      );
    },
    change(){
      this.redact(true);
      if(this.currentProfile !== null){
        this.profile.id = this.currentProfile.id;
        this.profile.weight = this.currentProfile.weight;
        this.profile.height = this.currentProfile.height;
        this.profile.age = this.currentProfile.age;
        this.profile.male = this.currentProfile.male;
        this.profile.activity = this.currentProfile.activity;
      }
    },
    getPr(){
      this.$store.dispatch('profileModule/getProfile', this.currentUser.id);
    }
  },
  mounted() {
    this.getPr();
  }
}

</script>
<style lang="scss" scoped>
.profile{
  min-height: 650px;
  margin-left: 10%;
  margin-right: 20%;
}
</style>