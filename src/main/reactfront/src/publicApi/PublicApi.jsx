import React, { useState, useEffect } from 'react';
import './App.css';

function PublicApi() {
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
export default PublicApi;