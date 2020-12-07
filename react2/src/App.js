import Axios from 'axios';
import React, {Component} from 'react';
import {Button} from 'reactstrap';
class App extends Component{
    state = {
        player : "Ready.",
        dealer: "Ready.",
    }

    PlayerOptionHit () {
        Axios.get('http://192.168.0.22:5542?playerChoice=hit').then((response)=>{
            console.log(response.data)
            this.setState({ player : response.data});
        });
    }

    PlayerOptionHold () {
        Axios.get('http://192.168.0.22:5542?playerChoice=hold').then((response)=>{
            console.log(response.data)
            this.setState({ player : response.data});
        });
    }
    Reset(){
     Axios.get('http://192.168.0.22:5542?reset=true').then((playerResponse)=>{
            console.log(playerResponse.data)
          this.setState({ player : "Ready."});
          this.setState({ dealer : "Ready."});
          this.setState({ playerOption : "hit"});
          this.setState({ dealerOption : "hit"});
          });
    }

    render() {

        return (
            <div className = "App Container">
                <Button color= "green" onClick={this.PlayerOptionHit.bind(this)}>Hit</Button>
                <Button color = "red" onClick={this.PlayerOptionHold.bind(this)}>Hold</Button>
                <Button color= "white" onClick={this.Reset.bind(this)}>Reset</Button>
                <h1>Player: {this.state.player} </h1>
                <h1>Dealer: {this.state.dealer}</h1>

            </div>
        )
    };
}
export default App;