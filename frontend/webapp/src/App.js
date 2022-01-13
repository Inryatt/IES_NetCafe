import logo from './logo.svg';
import './App.css';
import { Routes, Route } from 'react-router';
import { Col } from 'react-bootstrap';
import CustomNavbar from './components/CustomNavbar/CustomNavbar';
import DashboardPage from './pages/DashboardPage/DashboardPage';
import AlertsPage from './pages/AlertsPage/AlertsPage';
import HistoryPage from './pages/HistoryPage/HistoryPage';
import UsersPage from './pages/UsersPage/UsersPage';

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
        <br className="my-3" />
        <Col xs={12} md={11} className="mx-auto">
          <Routes>
            <Route path="/" >
              <Route path="dashboard/" element={<DashboardPage />} />
              <Route path="notifications/" element={<AlertsPage />} />
              <Route path="history/" element={<HistoryPage/>} />
              <Route path="users/" element={<UsersPage />} />
            </Route>
          </Routes>
        </Col>
      </div>
    </div>
  );
}

export default App;
