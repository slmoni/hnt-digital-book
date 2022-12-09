import { Component } from '@angular/core';

@Component({
  selector: 'app-orderform',
  templateUrl: './orderform.component.html',
  styleUrls: ['./orderform.component.css']
})
export class OrderformComponent {
  order = {
    itemName: "",
    price:0
  }
}
