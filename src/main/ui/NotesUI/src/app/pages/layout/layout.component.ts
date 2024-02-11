import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Note } from 'src/app/note.interface';

@Component({
  selector: 'app-layout',
  templateUrl: './layout.component.html',
  styleUrls: ['./layout.component.css']
})
export class LayoutComponent implements OnInit {
  notes:Note[]=[];

  constructor(private http:HttpClient) { }

  ngOnInit(): void {
    this.loadNotes();
  }

  
  loadNotes(){
    this.http.get('http://localhost:8088/api/notes').subscribe((res:any)=>{
      this.notes= res.data;
    })
  }

}
