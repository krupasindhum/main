import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'attendance-system-app';
  public employeeId:any;
  public longitude:number=0;
  public latitude:number=0;
  public _isGPSEnabled: boolean=false;
  public urlhost:string="http://localhost:9090//attendance?requestType=";

  constructor(private http:HttpClient) { 

  }

  ngOnInit() {
      this.getGeolocationData();
  }
 private getGeolocationData(){
    console.log("getGeolocationData");
    /* if (navigator.geolocation) {
      navigator.geolocation.getCurrentPosition(position => {
        this.latitude = position.coords.latitude;
        this.longitude = position.coords.longitude;        
        console.log("position", position)
        console.log(this.latitude+":::"+this.longitude) ;
      });
    }else{
      console.log("User not allowed")
    } */
    
if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(position => {
            this._isGPSEnabled = true;
            this.latitude = position.coords.latitude;
             this.longitude = position.coords.longitude;        
             console.log("position", position)
             console.log(this.latitude+":::"+this.longitude) ;
        }, (error: GeolocationPositionError) => {
            this._isGPSEnabled = false;
            switch (error.code) {
                case error.PERMISSION_DENIED:
                    console.error('User denied the request for Geolocation.');
                    alert('User denied the request for Geolocation.');
                    break;
                case error.POSITION_UNAVAILABLE:
                    console.error('Location information is unavailable.');
                    alert('Location information is unavailable.');
                    break;
                case error.TIMEOUT:
                    console.error('The request to get user location timed out.');
                    alert('The request to get user location timed out.');
                    break;
                default:
                    console.error('An unknown error occurred.');
                    alert('An unknown error occurred.');
                    break;
            }
        });
    } else {
        console.log('Geolocation is not supported by this browser.');
        alert('Geolocation is not supported by this browser.');
    }
  
  }
  
  public timeIn(){
    let empId=document.getElementById("empid") as HTMLInputElement | null;
    this.employeeId= empId?.value;
     console.log("time in",this.employeeId);
     let url=this.urlhost+"timein&empID="+this.employeeId+"&longitude="+this.longitude+"&latitude="+this.latitude;
     this.http.get(url).subscribe(
      (response) => { console.log(response); },
      (error) => { console.log(error); });
  }
  public timeOut(){
    console.log("time out");
    let empId=document.getElementById("empid") as HTMLInputElement | null;
    this.employeeId= empId?.value;
     console.log("time out",this.employeeId);
     let url=this.urlhost+"timeout&empID="+this.employeeId+"&longitude="+this.longitude+"&latitude="+this.latitude;
     this.http.get(url).subscribe(
      (response) => { console.log(response); },
      (error) => { console.log(error); });
  }
}
