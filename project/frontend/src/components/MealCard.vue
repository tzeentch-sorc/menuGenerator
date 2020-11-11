<template>
  <div>
    <md-card md-with-hover>
      <md-ripple>
        <md-card-header>This is card</md-card-header>
        <md-card-content>
          {{parentData}}
        </md-card-content>
      </md-ripple>

    </md-card>
  </div>
</template>
<script>

export default {
  name: 'MealCard',
  data() {
    return {
      content: ''
    }
  },
  props:{
    parentData: Object
  }
}

</script>
<style>

</style>