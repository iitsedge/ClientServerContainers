import Axios from 'axios';
import React, {Component} from 'react';
import styles from './index.css';
import {Button} from 'reactstrap';
class App extends Component{
    state = {
        player1 : "Ready.",
        player2: "Ready.",
        disabled: false
    }

    Player1OptionHit () {
        Axios.get('http://10.255.158.132:5542?playerChoice=hit1').then((response)=>{
            console.log(response.data)
            this.setState({ player1 : response.data});
        });
    }

    Player1OptionHold () {
        Axios.get('http://10.255.158.132:5542?playerChoice=hold1').then((response)=>{
            console.log(response.data)
            this.setState({ player1 : response.data});
            if (response.data==="WINNER"||response.data==="LOSER"||response.data==="TIE"){
                this.setState({disabled:true})
            }
        });
    }

    Player2OptionHit () {
        Axios.get('http://10.255.158.132:5542?playerChoice=hit2').then((response)=>{
            console.log(response.data)
            this.setState({ player2 : response.data});
        });
    }

    Player2OptionHold () {
        Axios.get('http://10.255.158.132?playerChoice=hold2').then((response)=>{
            console.log(response.data)
            this.setState({ player2 : response.data});
            if (response.data==="WINNER"||response.data==="LOSER"||response.data==="TIE"){
                this.setState({disabled:true})
            }
        });
    }

    Reset(){
     Axios.get('http://10.255.158.132:5542?reset=true').then((playerResponse)=>{
            console.log(playerResponse.data)
          this.setState({ player1 : "Ready."});
          this.setState({ player2 : "Ready."});
          this.setState({disabled:false})
          });
    }

    render() {

        return (
            <div className = "App Container">
                <h1>Player 1: {this.state.player1} </h1>
                <Button color= "success" disabled={this.state.disabled===true} onClick={this.Player1OptionHit.bind(this)}>Hit</Button>
                <Button color = "danger" disabled={this.state.disabled===true} onClick={this.Player1OptionHold.bind(this)}>Hold</Button>
                <h1>Player 2: {this.state.player2}</h1>
                <Button color= "success" disabled={this.state.disabled===true} onClick={this.Player2OptionHit.bind(this)}>Hit</Button>
                <Button color = "danger" disabled={this.state.disabled===true} onClick={this.Player2OptionHold.bind(this)}>Hold</Button>
                <h2>Start Over?</h2>
                <Button color= "secondary" onClick={this.Reset.bind(this)}>Reset</Button>

            </div>
        )
    };
}
export default App;