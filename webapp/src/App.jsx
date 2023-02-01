import React from "react"
import { BrowserRouter as Router, Route, Routes, useParams } from "react-router-dom"

import Diner from "./pages/diner"
import Management from "./pages/management"
import MainPage from "./pages/mainpage"
import EateryPage from "./pages/eaterypage"
import PlacePage from "./pages/place"

function App() {
  return (
    <Router>
      <Routes>
          <Route exact path="/" element={<MainPage />}/>

          <Route path="/dia-diem/:place" element={<PlacePage />} />

          <Route path="/quan-an/:eatery" element={<EateryPage />} />
          
          <Route exact path="/management" element={<Management />}/>
          
          <Route path="/diner/:eateryId/:tableId" element={<Diner />}/>
          
          <Route path="*" element={<>NotFound</>}/>
        </Routes>
    </Router>
  )
}

export default App;
