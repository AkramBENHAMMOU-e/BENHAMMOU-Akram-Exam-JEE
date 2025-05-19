import { Credit } from './credit.model';
import { PropertyType } from './enums/property-type.enum';

export interface RealEstateCredit extends Credit {
  propertyType: PropertyType;
}
