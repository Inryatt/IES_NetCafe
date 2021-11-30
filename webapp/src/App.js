import logo from './logo.svg';
import './App.css';

import MachineListPage from './pages/MachineListPage/MachineListPage';
import CustomNavbar from './components/CustomNavbar/CustomNavbar';
import { Routes, Route } from 'react-router';

function App() {
  return (
    <div>
      <link
        rel="stylesheet"
        href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
        integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
        crossOrigin="anonymous"
      />

      <div className="App">
        <CustomNavbar />

        <Routes>
          <Route path="/" element={<MachineListPage />} />
        </Routes>
      </div>
    </div>
  );
}

export default App;
