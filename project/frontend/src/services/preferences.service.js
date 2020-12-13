import axios from 'axios';
import authHeader from "@/services/auth-header";
const API_URL = 'http://localhost:5002/api/preference';

class PreferencesService{
    getPref(userId, nextPage) {
        return axios.get(`${API_URL}/${userId}?${nextPage}&size=20`, {headers: authHeader()});
    }
    addPref(userId, id, ban) {
        return axios.post(`${API_URL}/add/${ban}`, new URLSearchParams([["id", id], ["userId", userId]]),{headers: authHeader()});
    }
    deletePref(id, userId){
        return axios.post(`${API_URL}/remove`, new URLSearchParams([["id", id], ["userId", userId]]),{headers: authHeader()});
    }
}

export default new PreferencesService();