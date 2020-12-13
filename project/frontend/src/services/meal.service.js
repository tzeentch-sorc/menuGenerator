import axios from 'axios';
const API_URL = 'http://localhost:5002/api/meal';

class MealService {
  getAll(pageNum, params){
    if(params != null && params !== '')
      return axios.get( `${API_URL}?page=${pageNum}&size=15`, {params});
    else return axios.get( `${API_URL}?page=${pageNum}&size=15`);
  }

  getById(id) {
    return axios.get( `${API_URL}/${id}`);
  }

  getRandom(){
    return axios.get( `${API_URL}/random`);
  }
}

export default new MealService();

