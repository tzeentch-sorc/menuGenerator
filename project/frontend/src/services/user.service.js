import axios from 'axios';
import authHeader from './auth-header';
import baseUrl from "@/services/baseUrl";
const API_URL =  baseUrl + '/api/user/';

class UserService {
  getUserBoard() {
    return axios.get(API_URL, { headers: authHeader() });
  }

  // getModeratorBoard() {
  //   return axios.get(API_URL + 'mod', { headers: authHeader() });
  // }
  //
  // getAdminBoard() {
  //   return axios.get(API_URL + 'admin', { headers: authHeader() });
  // }
}

export default new UserService();
