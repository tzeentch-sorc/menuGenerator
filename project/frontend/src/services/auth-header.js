import store from "../store";

export default function authHeader() {
  let user = store.state.auth.user;
  if (user && user.token) {
    return { Authorization: `Bearer ${user.token}` };
  } else {
    return {};
  }
}
