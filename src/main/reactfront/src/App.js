import logo from './logo.svg';
import './App.css';
/*import React, {useState, useEffect} from 'react';

function App() {
  const [message, setMessage]=useState([]);
  useEffect(()=>{
    fetch("/hello")
        .then((res)=>{
          return res.json();
        })
        .then((data)=>{
          setMessage(data);
        });
  },[]);
  return (
      <div className="App">
        <header className="App-header">

          <ul>
            {message.map((v,idx)=><li key={`${idx}-${v}`}>{v}</li>)}
          </ul>
        </header>
      </div>
  );
}*/
import React, { useState, useEffect } from 'react';
import './App.css';

function App() {
    const [predictions, setPredictions] = useState([]);

    useEffect(() => {
        fetch("/api/predictions")
            .then(res => res.json())
            .then(data => {
                setPredictions(data);
            });
    }, []);

    return (
        <div className="App">
            <header className="App-header">
                {predictions.map((pred, idx) => (
                    <div key={idx}>
                        <p>Image URL: {pred.imageUrl}</p>
                        <ul>
                            {pred.predictions.map((p, index) => (
                                <li key={index}>{p.className}: {p.probability}</li>
                            ))}
                        </ul>
                    </div>
                ))}
            </header>
        </div>
    );
}
export default App;
