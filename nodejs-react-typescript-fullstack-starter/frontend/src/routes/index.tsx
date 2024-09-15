import { Inicio } from '../pages';
import { BrowserRouter, Routes, Route } from 'react-router-dom';

const Router = () => {
  return <>
  <BrowserRouter>
    <Routes>
      <Route  element={<Inicio/>} path='/' />
    </Routes>
  </BrowserRouter>
  </>
};

export default Router;
