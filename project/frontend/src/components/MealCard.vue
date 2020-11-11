<template>
  <div>
    <md-card md-with-hover class="md-primary">
      <md-ripple>
        <md-card-header>
          <div class="md-title">Card with hover effect</div>
        </md-card-header>
        <md-card-content>
          {{parentData.recipe}}
        </md-card-content>
        <md-card-actions>
          <md-button>Action</md-button>
        </md-card-actions>
      </md-ripple>

    </md-card>
  </div>
</template>
<script>

export default {
  name: 'MealCard',
  props:{
    parentData: Object
  }
}

</script>
<style lang="scss" scoped>
.md-card {
  width: 320px;
  margin: 4px;
  display: inline-block;
  vertical-align: top;
}
</style>