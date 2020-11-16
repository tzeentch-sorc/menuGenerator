import axios from 'axios';
const API_URL = 'http://localhost:8080/api/meals';

class MealService {
  getAll() {
    return axios.get(API_URL + "/all");
  }
  get(id) {
    return axios.get(API_URL + `/${id}`);
  }

  findByTitle(title) {
    return axios.get(API_URL + `/${title}`);
  }
}

export default new MealService();

