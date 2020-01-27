import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.css']
})
export class MainComponent implements OnInit {

  public imagesUrl;

  constructor(private router: Router) {

    this.imagesUrl = [
      'https://www.archermind.com/en/wp-content/uploads/2016/05/smart-home.jpg',
      'https://inhabitat.com/wp-content/blogs.dir/1/files/2018/01/Nanoleaf-Remote.jpg',
      'https://i0.wp.com/dreamhomeinsp.com/wp-content/uploads/2019/07/Smart-Home-Statistics.png?fit=1024%2C359&ssl=1',
      'http://www.apexhomeelectrical.co.uk/wp-content/uploads/2019/08/smart-home-automation-2.jpg',
      'http://unisons.lv/theme/unisons/public/images/header/big_images_home_1.jpg',
      'https://www.smarthouse.ua/wp-content/uploads/2019/07/1-1.jpg',
      'https://corezoid.com/images/smart-house.jpg',
      'http://www.pro-n.ru/img/pics/medium/6650.jpg'
    ];
  }

  ngOnInit() {
  }

}
