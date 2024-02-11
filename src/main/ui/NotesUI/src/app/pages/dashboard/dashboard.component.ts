import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent {

  notes:any[]=[];

  constructor(private http:HttpClient) { 
    this.loadNotes();
  }
  

  loadNotes(){
    this.http.get('http://localhost:8088/api/notes').subscribe((res:any)=>{
      this.notes= res.data;
    })
  }

  

}
