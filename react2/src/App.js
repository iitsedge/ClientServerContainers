import Axios from 'axios'
import React, {Component} from 'react'
import {Button} from 'reactstrap'

class App extends Component{

  state = {
    playerCard : "0"
  }
  DealerOption () {


  Axios.get('http://10.255.158.132:5542?DealerHit=true' ).then((response)=>{
      console.log(response.data)
      this.setState({ playerCard : response.data});
    });
  }

  PlayerOption () {

    Axios.get('http://10.255.158.132:5542?PlayerHit=true' ).then((playerResponse)=>{
        console.log(playerResponse.data)
      this.setState({ playerCard : playerResponse.data});
      });
    }



  render() {


    return (
      <div className = "App Container">
        <Button color= "success"  onClick={this.DealerOption.bind(this)} size = "sm" className="mr- 2">Dealer Hit</Button>
        <Button color = "danger"  onClick={this.PlayerOption.bind(this)}  size = "sm">Player Hit</Button>
        <h1> {this.state.playerCard} </h1>


      </div>
    )
  };
}

export default App;