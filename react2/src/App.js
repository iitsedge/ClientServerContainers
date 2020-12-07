import Axios from 'axios';
import React, {Component} from 'react';
import {Button} from 'reactstrap';
class App extends Component{
    state = {
        player : "hello",
        dealer: "hello",
        playerOption: "hit", //hit or hold
        dealerOption: "hit"   //hit or hold
    }
    DealerOption () {

        Axios.get('http://192.168.0.22:5542?dealerChoice=' + this.state.dealerOption  ).then((response)=>{
            console.log(response.data)
            this.setState({ dealer : response.data});
        });
    }
    PlayerOption () {
        Axios.get('http://192.168.0.22:5542?playerChoice=hit').then((response)=>{
            console.log(response.data)
            this.setState({ player : response.data});
        });
    }
    /*Reset(){
     Axios.get('http://10.255.158.132:5542?reset=true').then((playerResponse)=>{
            console.log(playerResponse.data)
          this.setState({ player : ""});
          this.setState({ dealer : ""});
          this.setState({ playerOption : "hit"});
          this.setState({ dealerOption : "hit"});
          });
    }*/

    render() {

        return (
            <div className = "App Container">
                <Button color= "green" onClick={this.PlayerOption.bind(this)}>Hit</Button>
                <Button color = "red" onClick={this.PlayerOption.bind(this)}>Hold</Button>
                <h1>Player: {this.state.player} </h1>
                <h1>Dealer: {this.state.dealer}</h1>

            </div>
        )
    };
}
export default App;