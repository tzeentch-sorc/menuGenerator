import axios from 'axios';
import authHeader from "@/services/auth-header";

const API_URL = 'http://localhost:5002/api/profile';

class ProfileService {
    getProfile(id) {
        return axios.post(API_URL + `/get/${id}`, null, {
            headers: authHeader()});
    }

    setProfile(userId, data){
        let params = new URLSearchParams([['profile', JSON.stringify(data)]])
        return axios.post(`${API_URL}/set/${userId}`, params, {
            headers: authHeader()
        });
    }

    // getModeratorBoard() {
    //   return axios.get(API_URL + 'mod', { headers: authHeader() });
    // }
    //
    // getAdminBoard() {
    //   return axios.get(API_URL + 'admin', { headers: authHeader() });
    // }
}

export default new ProfileService();
