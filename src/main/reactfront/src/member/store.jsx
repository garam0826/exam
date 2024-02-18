import { createStore, combineReducers } from 'redux';
import sessionReducer from './Reducer';

// 루트 리듀서
const rootReducer = combineReducers({
    session: sessionReducer,
});

// 스토어 생성
const store = createStore(rootReducer);

export default store;
