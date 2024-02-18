import React, { useState } from 'react';
import axios from 'axios';

const AnimalInfo = () => {
    const [animalInfos, setAnimalInfos] = useState([]);
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState(null);
    const [startDate, setStartDate] = useState('');
    const [endDate, setEndDate] = useState('');
    const [upkind, setUpkind] = useState('');
    const [uprCd, setUprCd] = useState('');
    const [orgCd, setOrgCd] = useState('');

    const fetchData = async () => {
        try {
            setLoading(true);
            setError(null);
            const response = await axios.get(`/api/animal/info?startDate=${startDate}&endDate=${endDate}&upkind=${upkind}&uprCd=${uprCd}&orgCd=${orgCd}`);
            setAnimalInfos(response.data);
        } catch (error) {
            console.error('Error fetching animal info:', error);
            setError(error);
        }
        setLoading(false);
    };

    return (
        <div>
            {loading && <div>Loading...</div>}
            {error && <div>Error: {error.message}</div>}
            <div>
                <label>Start Date:</label>
                <input type="text" value={startDate} onChange={(e) => setStartDate(e.target.value)} />
            </div>
            <div>
                <label>End Date:</label>
                <input type="text" value={endDate} onChange={(e) => setEndDate(e.target.value)} />
            </div>
            <div>
                <label>Upkind:</label>
                <input type="text" value={upkind} onChange={(e) => setUpkind(e.target.value)} />
            </div>
            <div>
                <label>UprCd:</label>
                <input type="text" value={uprCd} onChange={(e) => setUprCd(e.target.value)} />
            </div>
            <div>
                <label>OrgCd:</label>
                <input type="text" value={orgCd} onChange={(e) => setOrgCd(e.target.value)} />
            </div>
            <button onClick={fetchData}>Fetch Animal Info</button>
            {animalInfos.length === 0 && !loading && !error && <div>No data available</div>}
            <ul>
                {animalInfos.map((animalInfo, index) => (
                    <li key={index}>
                        <p>Desertion No: {animalInfo.desertionNo}</p>
                        <p>File Name: <img src={animalInfo.filename} alt="Animal"/></p>
                        <p>Happen Date: {animalInfo.happenDt}</p>
                        <p>Happen Place: {animalInfo.happenPlace}</p>
                        <p>KindCd: {animalInfo.kindCd}</p>
                        <p>ColorCd: {animalInfo.colorCd}</p>
                        <p>Age: {animalInfo.age}</p>
                        <p>Weight: {animalInfo.weight}</p>
                        <p>Notice No: {animalInfo.noticeNo}</p>
                        <p>Notice Start Date: {animalInfo.noticeSdt}</p>
                        <p>Notice End Date: {animalInfo.noticeEdt}</p>
                        <p>Popfile: <img src={animalInfo.popfile} alt="Animal"/></p>
                        <p>Process State: {animalInfo.processState}</p>
                        <p>SexCd: {animalInfo.sexCd}</p>
                        <p>NeuterYn: {animalInfo.neuterYn}</p>
                        <p>Special Mark: {animalInfo.specialMark}</p>
                        <p>Care Name: {animalInfo.careNm}</p>
                        <p>Care Tel: {animalInfo.careTel}</p>
                        <p>Care Address: {animalInfo.careAddr}</p>
                        <p>Org Name: {animalInfo.orgNm}</p>
                        <p>Charge Name: {animalInfo.chargeNm}</p>
                        <p>Office Tel: {animalInfo.officetel}</p>
                    </li>
                ))}
            </ul>
        </div>
    );
};

export default AnimalInfo;
