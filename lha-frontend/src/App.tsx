import {useEffect, useState} from 'react'
import './App.css'

type Business = {
    businessName: String,
    businessAddress: String,
    openingHours: BusinessOpeningHours[]
};

type BusinessOpeningHours = {
    name: String,
    start: String,
    end: String,
    type: String
};

function App() {
  const [businesess, setBusinesses] = useState<Array<Business>>([]);

  useEffect(() =>  {
        // TODO: The backend uri ought to be configured from environment variables
        fetch("https://localhost:8443/businesses")
            .then(resp => resp.json())
            .then(resp => setBusinesses(resp))
            .catch(e => console.log(e));
  }, [])

  return (
    <>
      <div>
          {
              businesess.map(b =>
                  <div className={"business-card"}>
                      <p className={"business-name"}>
                          {b.businessName}
                      </p>
                      <p className={"business-address"}>
                          {b.businessAddress}
                      </p>
                      <div className={"business-open-hours"}>
                          {

                              b.openingHours
                                  .map(oh =>
                                  <div className={"business-open-hour"}>
                                      <p className={"business-open-hour-days"}> {oh.name} </p>
                                      <div className={"business-open-hour-time"}>
                                          <span>{oh.type}</span> <span>{oh.start}</span> - <span>{oh.end}</span>
                                      </div>
                                  </div>
                              )
                          }
                      </div>
                  </div>
              )
          }
      </div>
    </>
  )
}

export default App
